require(["dojo/ready","dijit/form/Select","dojo/data/ItemFileWriteStore","dojo/data/ItemFileReadStore","dojox/grid/DataGrid",
		  "dijit/form/Form","dijit/form/Button","dijit/Toolbar",
		  "dijit/ToolbarSeparator","dijit/form/ComboBox","dojo/parser","dojox/grid/cells/dijit"],
			function(ready,Select,ItemFileWriteStore,ItemFileReadStore,DataGrid,Form,Button,Toolbar,ToolbarSeparator,ComboBox,parse){

	    //@Reference
	    var gridStat = new tuscany.sca.Reference("gridStatService");
		var contentpane = 'pane1'
		var tjstore;
		var trId;
		getId = function(trid){
			trId = trid;
		};
		back = function() {
			if(dijit.byId('form1').validate()){
				var obj = dijit.byId('form1').get('value');
				dijit.byId('myStackContainer').back();
			}
		};
		forward = function(){
			if(dijit.byId('form1').validate()){
				var obj = dijit.byId('form1').get('value');
				dijit.byId('myStackContainer').forward();
			}
		};
		save = function(){
			form3.reset();
			var formdata = form3.get('value');
//			var data = {type:formdata.}
			var dd;
		}
		createTjsz = function(){
			var toolbar = dojo.byId('toolbar');
			toolbar.setAttribute('dojoType','dijit.Toolbar');
			var tjfs = dojo.byId('tjfs');//统计方式
			tjfs.setAttribute("dojoType", "dijit.form.Select");
			var gfx = dojo.byId('gfx');
			gfx.setAttribute('dojoType','dijit/ToolbarSeparator');
			parse.instantiate([toolbar,tjfs,gfx]);
			var fsstore = new ItemFileReadStore({url:"js/tjfs.json"});
			dijit.byId('tjfs').setStore(fsstore);
		};
		addRow = function(){
			var length =dojo.byId('tjgrid').rows.length;
			var tr=document.createElement("tr");
			tr.setAttribute("onclick","getId("+length+")");
			tr.id=length;
			var fieldmark;
			var tjlxmark;
			for(i=1;i<=3;i++){
				var td=document.createElement("td");
				if(i == 1){
					td.innerHTML = length;
				}else if(i == 2){
					fieldmark="field"+length;
					td.innerHTML = "<div id='"+fieldmark+"'></div>"
				}else if(i == 3){
					tjlxmark="tjlx"+length;
					td.innerHTML = "<div id='"+tjlxmark+"'></div>"
				}
				tr.appendChild(td);
			}
			dojo.byId("tjgrid").appendChild (tr);
			createFieldSelect(fieldmark);
			createTjlxSelect(tjlxmark);
		};
		createFieldSelect = function(mark){
			var fieldstore = new ItemFileReadStore({url:"js/field.json"});
//			var tjlxstore = new ItemFileReadStore({url:"js/tjlx.json"});
			var s = new Select({
	            name: mark,
	            store:fieldstore
	        },mark);
			s.startup();
		};
		createTjlxSelect = function(mark){
			var tjlxstore = new ItemFileReadStore({url:"js/tjlx.json"});
			var t = new Select({
	            name: mark,
	            options: [
	                { label: "总计", value: "count" , selected: true },
	                { label: "和", value: "sum"},
	            ]
	        },mark);

		};
		delRow = function(){
			dojo.byId('tjgrid').deleteRow(trId);//table10--表格id
		};
		ready(function() {
			createTjsz();
		});
	});