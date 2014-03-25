package nsp_jet.compiled;

import org.eclipse.jet.JET2Context;
import org.eclipse.jet.JET2Template;
import org.eclipse.jet.JET2Writer;
import org.eclipse.jet.taglib.RuntimeTagElement;
import org.eclipse.jet.taglib.TagInfo;

public class _jet_main implements JET2Template {
    private static final String _jetns_c = "org.eclipse.jet.controlTags"; //$NON-NLS-1$
    private static final String _jetns_ws = "org.eclipse.jet.workspaceTags"; //$NON-NLS-1$

    public _jet_main() {
        super();
    }

    private static final String NL = System.getProperty("line.separator"); //$NON-NLS-1$
    
    private static final TagInfo _td_c_setVariable_8_1 = new TagInfo("c:setVariable", //$NON-NLS-1$
            8, 1,
            new String[] {
                "var", //$NON-NLS-1$
                "select", //$NON-NLS-1$
            },
            new String[] {
                "org.eclipse.jet.taglib.control.iterateSetsContext", //$NON-NLS-1$
                "true()", //$NON-NLS-1$
            } );
    private static final TagInfo _td_c_if_29_1 = new TagInfo("c:if", //$NON-NLS-1$
            29, 1,
            new String[] {
                "test", //$NON-NLS-1$
            },
            new String[] {
                "isVariableDefined('org.eclipse.jet.resource.project.name')", //$NON-NLS-1$
            } );
    private static final TagInfo _td_ws_file_30_5 = new TagInfo("ws:file", //$NON-NLS-1$
            30, 5,
            new String[] {
                "template", //$NON-NLS-1$
                "path", //$NON-NLS-1$
            },
            new String[] {
                "templates/dump.jet", //$NON-NLS-1$
                "{$org.eclipse.jet.resource.project.name}/dump.xml", //$NON-NLS-1$
            } );

    public void generate(final JET2Context context, final JET2Writer __out) {
        JET2Writer out = __out;
        // Main entry point for nsp_jet 
        out.write(NL);         
        // 
        //  Let c:iterate tags set the XPath context object.
        //  For 100% compatibility with JET 0.9.x or earlier, remove this statement
        // 
        RuntimeTagElement _jettag_c_setVariable_8_1 = context.getTagFactory().createRuntimeTag(_jetns_c, "setVariable", "c:setVariable", _td_c_setVariable_8_1); //$NON-NLS-1$ //$NON-NLS-2$
        _jettag_c_setVariable_8_1.setRuntimeParent(null);
        _jettag_c_setVariable_8_1.setTagInfo(_td_c_setVariable_8_1);
        _jettag_c_setVariable_8_1.doStart(context, out);
        _jettag_c_setVariable_8_1.doEnd();
        out.write(NL);         
        // 
        //TODO: traverse input model, performing calculations and storing 
        //the results as model annotations via c:set tag 
        out.write(NL);         
        out.write(NL);         
        //
        //TODO: traverse annotated model, performing text generation actions 
        //such as ws:file, ws:folder and ws:project 
        out.write("   ");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
        // For debug purposes, dump the annotated input model in 
        //   the root of the project containing the original input model.
        //   
        //   Note that model formatting may not be identical, and that in
        //   the case of non-XML input models, the dump may look quite different.
        out.write(NL);         
        RuntimeTagElement _jettag_c_if_29_1 = context.getTagFactory().createRuntimeTag(_jetns_c, "if", "c:if", _td_c_if_29_1); //$NON-NLS-1$ //$NON-NLS-2$
        _jettag_c_if_29_1.setRuntimeParent(null);
        _jettag_c_if_29_1.setTagInfo(_td_c_if_29_1);
        _jettag_c_if_29_1.doStart(context, out);
        while (_jettag_c_if_29_1.okToProcessBody()) {
            out.write("    ");  //$NON-NLS-1$        
            RuntimeTagElement _jettag_ws_file_30_5 = context.getTagFactory().createRuntimeTag(_jetns_ws, "file", "ws:file", _td_ws_file_30_5); //$NON-NLS-1$ //$NON-NLS-2$
            _jettag_ws_file_30_5.setRuntimeParent(_jettag_c_if_29_1);
            _jettag_ws_file_30_5.setTagInfo(_td_ws_file_30_5);
            _jettag_ws_file_30_5.doStart(context, out);
            _jettag_ws_file_30_5.doEnd();
            out.write(NL);         
            _jettag_c_if_29_1.handleBodyContent(out);
        }
        _jettag_c_if_29_1.doEnd();
    }
}
