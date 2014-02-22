/* 黄鑫 */
require(["dojo/ready"],function($ready){
	capec.managedHub = new OpenAjax.hub.ManagedHub({
		onPublish: function($topic, $data, $publishContainer, $subscribeContainer){
			return true;
		},
		onSubscribe: function($topic, $container){
			return true;
		},
		onUnsubscribe: function($topic, $container){
			return true;
		},
		onSecurityAlert: function($source, $alertType){
			console.log($alertType);
		}
	});
});