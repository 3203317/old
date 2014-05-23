$(function() {
	var default_ajaxSetupOption = {
		dataType : "json",
		type : "POST",
		async : true,
		cache : false,
		timeout : 30000,
		complete : function(jqXHR, textStatus) {
			if (textStatus == 'timeout') {
				$.jBox.alert("连接超时", '提示');
				// alert('提示信息','连接超时');
			} else if (textStatus == 'success') {
				var responseText = jqXHR.responseText;
				try {
					var d = $.parseJSON(responseText);
					if (d.status == false) {
						$.jBox.alert(d.msg, '提示');
						if(d.result=='0'){
							alert(1);
							//session 失效
							window.location.href = modelPath;
						}
						// alert(d.msg);
					}
				} catch (e) {
				}
			}
		}
	}
	$.ajaxSetup(default_ajaxSetupOption);
});

String.prototype.endWith = function(s) {
	if (s == null || s == "" || this.length == 0 || s.length > this.length)
		return false;
	if (this.substring(this.length - s.length) == s)
		return true;
	else
		return false;
	return true;
}

String.prototype.startWith = function(s) {
	if (s == null || s == "" || this.length == 0 || s.length > this.length)
		return false;
	if (this.substr(0, s.length) == s)
		return true;
	else
		return false;
	return true;
}

/** * 驼峰命名规范转换为用下划线连接 start */
String.prototype.underscoreName = function() {
	var str = this;
	if (str == '') {
		return str;
	}
	var result = "";
	var length = str.length;
	var _char = '';
	for ( var i = 0; i < length; i++) {
		_char = str.charAt(i);
		if (i == 0) {
			_char = _char.toLowerCase();
		}

		if (_char == '') {
			continue;
		}
		if (_char == _char.toUpperCase()) {
			result = result + "_" + _char.toLowerCase();
		} else {
			result = result + _char;
		}
	}
	return result;
};
