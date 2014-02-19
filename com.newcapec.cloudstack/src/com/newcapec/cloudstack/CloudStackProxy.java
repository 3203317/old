package com.newcapec.cloudstack;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.w3c.dom.Document;

public class CloudStackProxy {

	public static void main(String[] args) {
		CloudStackProxy cs = new CloudStackProxy("5FGNNyJelu8thywwfzLFGec1EBe72IdQOpJQfjp7zyKxrX6ZpBpdJPzUZRL8OZFzB-bJnp3oQLPBuf3RuqMV4w", "Op4T-wRkP7yU5nzYwzR86GnI1uES31YWpMgIrkFLbOH17fVCteAhW7grQXLsemeW867bYya4dgMVIEB2JIDmwg");

		try {
			System.out.println(cs.listAccounts(null));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String secret;
	private String apikey;

	public CloudStackProxy(String new_secret, String new_apikey) {
		secret = new_secret;
		apikey = new_apikey;
	}

	// -----------------------------------------------------------------------------------------------------
	// public api starts here

	// Section: Virtual Machine
	public Document deployVirtualMachine(String serviceofferingid, String templateid, String zoneid, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deployVirtualMachine", optional);
		arguments.add(new NameValuePair("serviceofferingid", serviceofferingid));
		arguments.add(new NameValuePair("templateid", templateid));
		arguments.add(new NameValuePair("zoneid", zoneid));
		return Request(arguments);
	}

	public Document destroyVirtualMachine(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("destroyVirtualMachine", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document rebootVirtualMachine(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("rebootVirtualMachine", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document startVirtualMachine(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("startVirtualMachine", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document stopVirtualMachine(String id, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("stopVirtualMachine", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document resetPasswordForVirtualMachine(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("resetPasswordForVirtualMachine", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document changeServiceForVirtualMachine(String id, String serviceofferingid) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("changeServiceForVirtualMachine", null);
		arguments.add(new NameValuePair("id", id));
		arguments.add(new NameValuePair("serviceofferingid", serviceofferingid));
		return Request(arguments);
	}

	public Document updateVirtualMachine(String id, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("updateVirtualMachine", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document listVirtualMachines(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listVirtualMachines", optional);
		return Request(arguments);
	}

	public Document getVMPassword(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("getVMPassword", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	// Section: Template
	public Document createTemplate(String displaytext, String name, String ostypeid, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("createTemplate", optional);
		arguments.add(new NameValuePair("displaytext", displaytext));
		arguments.add(new NameValuePair("name", name));
		arguments.add(new NameValuePair("ostypeid", ostypeid));
		return Request(arguments);
	}

	public Document registerTemplate(String displaytext, String format, String hypervisor, String name, String ostypeid, String url, String zoneid, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("registerTemplate", optional);
		arguments.add(new NameValuePair("displaytext", displaytext));
		arguments.add(new NameValuePair("format", format));
		arguments.add(new NameValuePair("hypervisor", hypervisor));
		arguments.add(new NameValuePair("name", name));
		arguments.add(new NameValuePair("ostypeid", ostypeid));
		arguments.add(new NameValuePair("url", url));
		arguments.add(new NameValuePair("zoneid", zoneid));
		return Request(arguments);
	}

	public Document updateTemplate(String id, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("updateTemplate", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document copyTemplate(String id, String destzoneid, String sourcezoneid) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("copyTemplate", null);
		arguments.add(new NameValuePair("id", id));
		arguments.add(new NameValuePair("destzoneid", destzoneid));
		arguments.add(new NameValuePair("sourcezoneid", sourcezoneid));
		return Request(arguments);
	}

	public Document deleteTemplate(String id, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteTemplate", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document listTemplates(String templatefilter, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listTemplates", optional);
		arguments.add(new NameValuePair("templatefilter", templatefilter));
		return Request(arguments);
	}

	public Document updateTemplatePermissions(String id, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("updateTemplatePermissions", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document listTemplatePermissions(String id, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listTemplatePermissions", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document extractTemplate(String id, String mode, String zoneid, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("extractTemplate", optional);
		arguments.add(new NameValuePair("id", id));
		arguments.add(new NameValuePair("mode", mode));
		arguments.add(new NameValuePair("zoneid", zoneid));
		return Request(arguments);
	}

	// Section: ISO
	public Document attachIso(String id, String virtualmachineid) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("attachIso", null);
		arguments.add(new NameValuePair("id", id));
		arguments.add(new NameValuePair("virtualmachineid", virtualmachineid));
		return Request(arguments);
	}

	public Document detachIso(String virtualmachineid) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("detachIso", null);
		arguments.add(new NameValuePair("virtualmachineid", virtualmachineid));
		return Request(arguments);
	}

	public Document listIsos(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listIsos", optional);
		return Request(arguments);
	}

	public Document registerIso(String displaytext, String name, String url, String zoneid, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("registerIso", optional);
		arguments.add(new NameValuePair("displaytext", displaytext));
		arguments.add(new NameValuePair("name", name));
		arguments.add(new NameValuePair("url", url));
		arguments.add(new NameValuePair("zoneid", zoneid));
		return Request(arguments);
	}

	public Document updateIso(String id, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("updateIso", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document deleteIso(String id, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteIso", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document copyIso(String id, String destzoneid, String sourcezoneid) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("copyIso", null);
		arguments.add(new NameValuePair("id", id));
		arguments.add(new NameValuePair("destzoneid", destzoneid));
		arguments.add(new NameValuePair("sourcezoneid", sourcezoneid));
		return Request(arguments);
	}

	public Document updateIsoPermissions(String id, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("updateIsoPermissions", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document listIsoPermissions(String id, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listIsoPermissions", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document extractIso(String id, String mode, String zoneid, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("extractIso", optional);
		arguments.add(new NameValuePair("id", id));
		arguments.add(new NameValuePair("mode", mode));
		arguments.add(new NameValuePair("zoneid", zoneid));
		return Request(arguments);
	}

	// Section: Volume
	public Document attachVolume(String id, String virtualmachineid, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("attachVolume", optional);
		arguments.add(new NameValuePair("id", id));
		arguments.add(new NameValuePair("virtualmachineid", virtualmachineid));
		return Request(arguments);
	}

	public Document detachVolume(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("detachVolume", optional);
		return Request(arguments);
	}

	public Document createVolume(String name, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("createVolume", optional);
		arguments.add(new NameValuePair("name", name));
		return Request(arguments);
	}

	public Document deleteVolume(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteVolume", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document listVolumes(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listVolumes", optional);
		return Request(arguments);
	}

	public Document extractVolume(String id, String mode, String zoneid, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("extractVolume", optional);
		arguments.add(new NameValuePair("id", id));
		arguments.add(new NameValuePair("mode", mode));
		arguments.add(new NameValuePair("zoneid", zoneid));
		return Request(arguments);
	}

	// Section: Security Group
	public Document createSecurityGroup(String name, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("createSecurityGroup", optional);
		arguments.add(new NameValuePair("name", name));
		return Request(arguments);
	}

	public Document deleteSecurityGroup(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteSecurityGroup", optional);
		return Request(arguments);
	}

	public Document authorizeSecurityGroupIngress(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("authorizeSecurityGroupIngress", optional);
		return Request(arguments);
	}

	public Document revokeSecurityGroupIngress(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("revokeSecurityGroupIngress", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document listSecurityGroups(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listSecurityGroups", optional);
		return Request(arguments);
	}

	// Section: Account
	public Document listAccounts(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listAccounts", optional);
		return Request(arguments);
	}

	// Section: Snapshot
	public Document createSnapshot(String volumeid, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("createSnapshot", optional);
		arguments.add(new NameValuePair("volumeid", volumeid));
		return Request(arguments);
	}

	public Document listSnapshots(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listSnapshots", optional);
		return Request(arguments);
	}

	public Document deleteSnapshot(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteSnapshot", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document createSnapshotPolicy(String intervaltype, String maxsnaps, String schedule, String timezone, String volumeid) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("createSnapshotPolicy", null);
		arguments.add(new NameValuePair("intervaltype", intervaltype));
		arguments.add(new NameValuePair("maxsnaps", maxsnaps));
		arguments.add(new NameValuePair("schedule", schedule));
		arguments.add(new NameValuePair("timezone", timezone));
		arguments.add(new NameValuePair("volumeid", volumeid));
		return Request(arguments);
	}

	public Document deleteSnapshotPolicies(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteSnapshotPolicies", optional);
		return Request(arguments);
	}

	public Document listSnapshotPolicies(String volumeid, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listSnapshotPolicies", optional);
		arguments.add(new NameValuePair("volumeid", volumeid));
		return Request(arguments);
	}

	// Section: Async job
	public Document queryAsyncJobResult(String jobid) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("queryAsyncJobResult", null);
		arguments.add(new NameValuePair("jobid", jobid));
		return Request(arguments);
	}

	public Document listAsyncJobs(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listAsyncJobs", optional);
		return Request(arguments);
	}

	// Section: Event
	public Document listEvents(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listEvents", optional);
		return Request(arguments);
	}

	public Document listEventTypes() throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listEventTypes", null);
		return Request(arguments);
	}

	// Section: Guest OS
	public Document listOsTypes(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listOsTypes", optional);
		return Request(arguments);
	}

	public Document listOsCategories(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listOsCategories", optional);
		return Request(arguments);
	}

	// Section: Service Offering
	public Document listServiceOfferings(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listServiceOfferings", optional);
		return Request(arguments);
	}

	// Section: Disk Offering
	public Document listDiskOfferings(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listDiskOfferings", optional);
		return Request(arguments);
	}

	// Section: SSH
	public Document registerSSHKeyPair(String name, String publickey, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("registerSSHKeyPair", optional);
		arguments.add(new NameValuePair("name", name));
		arguments.add(new NameValuePair("publickey", publickey));
		return Request(arguments);
	}

	public Document createSSHKeyPair(String name, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("createSSHKeyPair", optional);
		arguments.add(new NameValuePair("name", name));
		return Request(arguments);
	}

	public Document deleteSSHKeyPair(String name, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteSSHKeyPair", optional);
		arguments.add(new NameValuePair("name", name));
		return Request(arguments);
	}

	public Document listSSHKeyPairs(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listSSHKeyPairs", optional);
		return Request(arguments);
	}

	// Section: Address
	public Document associateIpAddress(String zoneid, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("associateIpAddress", optional);
		arguments.add(new NameValuePair("zoneid", zoneid));
		return Request(arguments);
	}

	public Document disassociateIpAddress(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("disassociateIpAddress", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document listPublicIpAddresses(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listPublicIpAddresses", optional);
		return Request(arguments);
	}

	// Section: Firewall
	public Document listPortForwardingRules(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listPortForwardingRules", optional);
		return Request(arguments);
	}

	public Document createPortForwardingRule(String ipaddressid, String privateport, String protocol, String publicport, String virtualmachineid, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("createPortForwardingRule", optional);
		arguments.add(new NameValuePair("ipaddressid", ipaddressid));
		arguments.add(new NameValuePair("privateport", privateport));
		arguments.add(new NameValuePair("protocol", protocol));
		arguments.add(new NameValuePair("publicport", publicport));
		arguments.add(new NameValuePair("virtualmachineid", virtualmachineid));
		return Request(arguments);
	}

	public Document deletePortForwardingRule(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deletePortForwardingRule", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document createFirewallRule(String ipaddressid, String protocol, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("createFirewallRule", optional);
		arguments.add(new NameValuePair("ipaddressid", ipaddressid));
		arguments.add(new NameValuePair("protocol", protocol));
		return Request(arguments);
	}

	public Document deleteFirewallRule(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteFirewallRule", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document listFirewallRules(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listFirewallRules", optional);
		return Request(arguments);
	}

	// Section: NAT
	public Document enableStaticNat(String ipaddressid, String virtualmachineid) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("enableStaticNat", null);
		arguments.add(new NameValuePair("ipaddressid", ipaddressid));
		arguments.add(new NameValuePair("virtualmachineid", virtualmachineid));
		return Request(arguments);
	}

	public Document createIpForwardingRule(String ipaddressid, String protocol, String startport, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("createIpForwardingRule", optional);
		arguments.add(new NameValuePair("ipaddressid", ipaddressid));
		arguments.add(new NameValuePair("protocol", protocol));
		arguments.add(new NameValuePair("startport", startport));
		return Request(arguments);
	}

	public Document deleteIpForwardingRule(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteIpForwardingRule", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document listIpForwardingRules(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listIpForwardingRules", optional);
		return Request(arguments);
	}

	public Document disableStaticNat(String ipaddressid) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("disableStaticNat", null);
		arguments.add(new NameValuePair("ipaddressid", ipaddressid));
		return Request(arguments);
	}

	// Section: Load Balancer
	public Document createLoadBalancerRule(String algorithm, String name, String privateport, String publicport, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("createLoadBalancerRule", optional);
		arguments.add(new NameValuePair("algorithm", algorithm));
		arguments.add(new NameValuePair("name", name));
		arguments.add(new NameValuePair("privateport", privateport));
		arguments.add(new NameValuePair("publicport", publicport));
		return Request(arguments);
	}

	public Document deleteLoadBalancerRule(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteLoadBalancerRule", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	// virtualmachineids is a comma-seperated list
	public Document removeFromLoadBalancerRule(String id, String virtualmachineids) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("removeFromLoadBalancerRule", null);
		arguments.add(new NameValuePair("id", id));
		arguments.add(new NameValuePair("virtualmachineids", virtualmachineids));
		return Request(arguments);
	}

	public Document assignToLoadBalancerRule(String id, String virtualmachineids) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("assignToLoadBalancerRule", null);
		arguments.add(new NameValuePair("id", id));
		arguments.add(new NameValuePair("virtualmachineids", virtualmachineids));
		return Request(arguments);
	}

	public Document listLoadBalancerRules(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listLoadBalancerRules", optional);
		return Request(arguments);
	}

	public Document listLoadBalancerRuleInstances(String id, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listLoadBalancerRuleInstances", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document updateLoadBalancerRule(String id, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("updateLoadBalancerRule", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	// Section: VM group
	public Document createInstanceGroup(String name, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("createInstanceGroup", optional);
		arguments.add(new NameValuePair("name", name));
		return Request(arguments);
	}

	public Document deleteInstanceGroup(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteInstanceGroup", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document updateInstanceGroup(String id, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("updateInstanceGroup", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document listInstanceGroups(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listInstanceGroups", optional);
		return Request(arguments);
	}

	// Section: Network
	public Document createNetwork(String displaytext, String name, String networkofferingid, String zoneid, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("createNetwork", optional);
		arguments.add(new NameValuePair("displaytext", displaytext));
		arguments.add(new NameValuePair("name", name));
		arguments.add(new NameValuePair("networkofferingid", networkofferingid));
		arguments.add(new NameValuePair("zoneid", zoneid));
		return Request(arguments);
	}

	public Document deleteNetwork(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteNetwork", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document listNetworks(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listNetworks", optional);
		return Request(arguments);
	}

	public Document restartNetwork(String id, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("restartNetwork", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public Document updateNetwork(String id, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("updateNetwork", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	// Section: VPN
	public Document createRemoteAccessVpn(String publicipid, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("createRemoteAccessVpn", optional);
		arguments.add(new NameValuePair("publicipid", publicipid));
		return Request(arguments);
	}

	public Document deleteRemoteAccessVpn(String publicipid) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteRemoteAccessVpn", null);
		arguments.add(new NameValuePair("publicipid", publicipid));
		return Request(arguments);
	}

	public Document listRemoteAccessVpns(String publicipid, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listRemoteAccessVpns", optional);
		arguments.add(new NameValuePair("publicipid", publicipid));
		return Request(arguments);
	}

	public Document addVpnUser(String password, String username, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("addVpnUser", optional);
		arguments.add(new NameValuePair("password", password));
		arguments.add(new NameValuePair("username", username));
		return Request(arguments);
	}

	public Document removeVpnUser(String username, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("removeVpnUser", optional);
		arguments.add(new NameValuePair("username", username));
		return Request(arguments);
	}

	public Document listVpnUsers(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listVpnUsers", optional);
		return Request(arguments);
	}

	// Section: Hypervisor
	public Document listHypervisors(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listHypervisors", optional);
		return Request(arguments);
	}

	// Section: Zone
	public Document listZones(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listZones", optional);
		return Request(arguments);
	}

	// Section: Network Offering
	public Document listNetworkOfferings(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listNetworkOfferings", optional);
		return Request(arguments);
	}

	// Section: Configuration
	public Document listCapabilities() throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listCapabilities", null);
		return Request(arguments);
	}

	// Section: Limit
	public Document listResourceLimits(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listResourceLimits", optional);
		return Request(arguments);
	}

	// Section: Cloud Identifier
	public Document getCloudIdentifier(String userid) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("getCloudIdentifier", null);
		arguments.add(new NameValuePair("userid", userid));
		return Request(arguments);
	}

	// Section: Login
	public Document login(String username, String password, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("login", optional);
		arguments.add(new NameValuePair("username", username));
		arguments.add(new NameValuePair("password", password));
		return Request(arguments);
	}

	// Section: Logout
	public Document logout() throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("logout", null);
		return Request(arguments);
	}

	// -----------------------------------------------------------------------------------------------------

	private LinkedList<NameValuePair> newQueryValues(String command, HashMap<String, String> optional) {
		LinkedList<NameValuePair> queryValues = new LinkedList<NameValuePair>();
		queryValues.add(new NameValuePair("command", command));
		queryValues.add(new NameValuePair("apiKey", apikey));
		if (optional != null) {
			Iterator optional_it = optional.entrySet().iterator();
			while (optional_it.hasNext()) {
				Map.Entry pairs = (Map.Entry) optional_it.next();
				queryValues.add(new NameValuePair((String) pairs.getKey(), (String) pairs.getValue()));
			}
		}
		return queryValues;
	}

	private String sign_request(LinkedList<NameValuePair> queryValues) throws Exception {
		Collections.sort(queryValues, new Comparator<NameValuePair>() {
			public int compare(NameValuePair o1, NameValuePair o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		String query_string = EncodingUtil.formUrlEncode(queryValues.toArray(new NameValuePair[0]), "UTF-8");

		System.out.println(query_string);

		Mac mac = Mac.getInstance("HmacSHA1");
		SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA1");

		// 用给定密钥初始化 Mac 对象
		mac.init(secret_key);
		byte[] digest = mac.doFinal(query_string.toLowerCase().getBytes());

		String oauth = new BASE64Encoder().encode(digest);
		
		System.out.println(java.net.URLEncoder.encode(oauth));

		return "gual8aGut0Fb%2FLP3k%2Bx7P%2FlCi1E%3D";
	}

	private Document Request(LinkedList<NameValuePair> queryValues) throws Exception {
		HttpMethod method;
		method = makeHttpGet(queryValues);
		return executeGet(method);
	}

	private HttpMethod makeHttpGet(LinkedList<NameValuePair> queryValues) throws Exception {
		String query_signature = sign_request(queryValues);
		queryValues.add(new NameValuePair("signature", query_signature));

		HttpMethod method = new GetMethod("http://192.168.102.21:8080/client/api");
		method.setFollowRedirects(true);
		method.setQueryString(queryValues.toArray(new NameValuePair[0]));
		

		return method;
	}

	private Document executeGet(HttpMethod method) throws HttpException, IOException, Exception {
		HttpClient client = new HttpClient();

		Document response;
		method.setQueryString("apiKey=Op4T-wRkP7yU5nzYwzR86GnI1uES31YWpMgIrkFLbOH17fVCteAhW7grQXLsemeW867bYya4dgMVIEB2JIDmwg&command=listAccounts&signature=gual8aGut0Fb%2FLP3k%2Bx7P%2FlCi1E%3D");
		
		client.executeMethod(method);
		
		
		System.out.println(method.getQueryString());

		response = handleResponse(method.getResponseBodyAsStream());

		// clean up the connection resources
		method.releaseConnection();

		return response;
	}

	public class CloudStackProxyException extends Exception {
		CloudStackProxyException(String errorcode, String errortext) {
			super(errorcode + ": " + errortext);
		}
	}

	private Document handleResponse(InputStream ResponseBody) throws javax.xml.parsers.ParserConfigurationException, org.xml.sax.SAXException, java.io.IOException, CloudStackProxyException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(ResponseBody);

		if (doc.getDocumentElement().getNodeName().equals("errorresponse")) {
			throw new CloudStackProxyException(doc.getElementsByTagName("errorcode").item(0).getChildNodes().item(0).getNodeValue(), doc.getElementsByTagName("errortext").item(0).getChildNodes().item(0).getNodeValue());
		}

		return doc;
	}

}
