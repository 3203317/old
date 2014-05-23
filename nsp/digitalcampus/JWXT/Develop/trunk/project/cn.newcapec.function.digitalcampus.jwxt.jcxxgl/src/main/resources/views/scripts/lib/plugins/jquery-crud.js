(function($) {
	$.fn.crud = function(options) {
		$div = $(this);
		var className = "jquery-crud";
		if($div.hasClass(className)){
			//已经绑定过了
			debug("已经绑定过了，不可以重复绑定的");
			return false;
		}
		
		var id = $(this).attr("id");
		$div.addClass(className);
		options.divId = id;
		options.action = defaultsOptions.action + $(this).attr("action");
		debug(options);
		init(options);
	}

	function init(o) {
		options = $.extend(true,{},defaultsOptions,o);
		if($div.length==0){
			debug("没有找到div");
			return false;
		}
		debug(options);
		_self["options"] = options;
		
		$.each(options,function(i,obj){
			var type = typeof obj;
			if(type=='object' && obj.cla && obj.handler){
				//绑定click事件
				var e = $div.find("." + obj.cla);
				if(e.length>0){
					e.click(obj.handler);
					if(obj.bindSaveMethod){
						obj.bindSaveMethod(e,options);
					}
				}
			}
		});
		//执行以下query
		$div.find(".search").trigger("click");
	}
	// 私有函数：debugging
	function debug(msg) {
		if (window.console && window.console.log) {
			window.console.log(msg);
		}
	}

	function save() {
		var $form = $(this).closest(".modal-content").find("form");
		if ($form.valid() == false) {
			return false;
		}
		if (options.save.onBefore) {
			try {
				options.save.onBefore($form);
			} catch (e) {
			}
		}
		var url = options.action + "/" + options.save.methord;
		var data = $form.serializeObjectForm();

		//修改人
		data.editUserId = loginUId;
		
		var result = false;
		$.ajax({
			url : url,
			data : data,
			async : false,
			success : function(d) {
				if (options.save.onAfter) {
					try {
						result = options.save.onAfter(d,$div);
					} catch (e) {
					}
				} else {
					var id = d.result.id;
					if(id=='-2'){
						$.jBox.alert(d.msg, '提示');
					}else if (d != '') {
						// 保存成功
						$div.find(".queryForm").find(".search").trigger("click");
						result = true;
					}
				}
			}
		});
		return result;
	}

	function del() {
		var $tr = $div.find(".table-list tbody .selected_tr");
		if ($tr && $tr.length > 0) {
			var id = $tr.attr("value");
			var url = options.action + "/" + options.del.methord;
			function submit(v, h, f) {
				if (v == 'ok') {
					// 删除
					$.ajax({
						url : url,
						data:"id=" + id,
						success : function(d) {
							if (d) {
								$div.find(".search").trigger("click");
							}
						}
					});
				}
				return true; // close
			}
			$.jBox.confirm("确认要删除该数据?", "提示", submit);
		} else {
			$.jBox.alert('请先选择数据', '提示');
			return false;
		}
	}

	function edit() {
		var $tr = $div.find(".table-list tbody .selected_tr");
		var targetDiv = $(this).attr("data-target");
		var $form = $(targetDiv).find("form");
		if ($tr && $tr.length > 0) {
			var id = $tr.attr("value");
			var url = options.action + "/" + options.edit.methord + "?id=" + id;
			$.ajax({
				url : url,
				success : function(d) {
					if (d.result) {
						$form.resetObjectForm(d.result);
						$form.validation();
						
						if(options.edit.onAfter){
							try {
								options.edit.onAfter(d,$form);
							} catch (e) {
							}
						}
					}
				}
			});
		} else {
			$.jBox.alert('请先选择数据', '提示');
			return false;
		}
	}

	//新增
	function add(){
		var targetDiv = $(this).attr("data-target");
		var $form = $(targetDiv).find("form");
		$form[0].reset();
		$form.validation();
	}
	
	function search() {
		var divId = options.divId;
		var $form = $div.find(".queryForm");
		var data = getQueryFormData($form);
		var url = options.action + "/" + options.search.methord;
		// var data = $form.serializeObjectForm();
		$.ajax({
			url : url,
			data : data,
			success : function(d) {
				var data = d.result;
				if (data.items) {
					var tbody = $div.find(".table-list tbody");
					tbody.empty();
					$("#" + options.search.tmpl).tmpl(data.items).appendTo(tbody);
					var html = getPaginationHtml(data, divId);
					$div.find(".pagination").html(html);
				}
			}
		});
	}

	var _self={};
	var $div;

	var options = {};

	// 默认参数
	var defaultsOptions = {
		divId : "currentDataDiv",
		//action : modelPath + "/",
		save : {
			methord : "save",
			cla : "save",
			handler : save,
			onBefore : null,
			onAfter : null
		},
		del : {
			methord : "deleteById",
			cla : "del",
			handler : del,
			onBefore : null,
			onAfter : null
		},
		edit : {
			bindSaveMethod:function(el,options){
				var targeDivId = el.attr("data-target");
				var $targetDiv = $(targeDivId);
				if($targetDiv){
					$targetDiv.find("." + options.save.cla).unbind("click").click(options.save.handler);
				}
			},
			methord : "findById",
			cla : "edit",
			handler : edit,
			onBefore : null,
			onAfter : null
		},
		add:{
			bindSaveMethod:function(el,options){
				var targeDivId = el.attr("data-target");
				var $targetDiv = $(targeDivId);
				if($targetDiv){
					$targetDiv.find("." + options.save.cla).unbind("click").click(options.save.handler);
				}
			},
			cla:"add",
			handler:add,
			onBefore:null,
			onAfter:null
		},
		search : {
			tmpl : "listTmpl",
			methord : "queryPage",
			cla : "search",
			handler : search,
			onBefore : null,
			onAfter : null
		}
	};
})(jQuery);