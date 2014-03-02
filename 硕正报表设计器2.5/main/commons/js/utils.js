capec.utils = {};

/**
 * 数据转为为树数据
 */
capec.utils.data2TreeObject = function($data, $idVal, $idName, $parentIdName,
		$textName, $itemName) {
	var __treeObj = {};
	__treeObj[$idName] = $idVal;
	__treeObj[$textName] = "ROOT";

	capec.utils.data2TreeObject_1($data, $idVal, $idName, $parentIdName,
			$itemName, __treeObj);
	return __treeObj;
};

capec.utils.data2TreeObject_1 = function($data, $idVal, $idName, $parentIdName,
		$itemName, $treeObj) {
	var __treeArr = [];

	for ( var __i_3 = 0, __j_3 = $data.items.length; __i_3 < __j_3; __i_3++) {
		var __item_4 = $data.items[__i_3];

		if (__item_4[$parentIdName] == $idVal) {
			__treeArr.push(__item_4);

			capec.utils.data2TreeObject_1($data, __item_4[$idName], $idName,
					$parentIdName, $itemName, __item_4);
		}
	}
	if (__treeArr.length > 0)
		$treeObj[$itemName] = __treeArr;
};