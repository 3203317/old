

String.prototype.replaceAll = function($oldText,$replaceText){
	return this.replace(new RegExp($oldText,"gm"),$replaceText);
};
