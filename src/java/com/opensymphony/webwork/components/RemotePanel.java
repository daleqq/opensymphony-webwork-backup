package com.opensymphony.webwork.components;

import com.opensymphony.xwork.util.OgnlValueStack;
import com.opensymphony.webwork.components.ContentPane;
import com.opensymphony.webwork.components.JavascriptEmitter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * User: plightbo
 * Date: Jul 20, 2005
 * Time: 7:22:54 AM
 */
public class RemotePanel extends Div implements ContentPane, JavascriptEmitter {
    private static final Log LOG = LogFactory.getLog(RemotePanel.class);

    public static final String TEMPLATE = "remotetab";
    public static final String TEMPLATE_CLOSE = "remotetab-close";
    public static final String COMPONENT_NAME = RemotePanel.class.getName();

    protected String tabName;
    protected String subscribeTopicName;

    public RemotePanel(OgnlValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    public String getDefaultOpenTemplate() {
        return TEMPLATE;
    }

    protected String getDefaultTemplate() {
        return TEMPLATE_CLOSE;
    }

    public void end(Writer writer) {
        TabbedPanel tabbedPanel = ((TabbedPanel) findAncestor(TabbedPanel.class));
        subscribeTopicName = tabbedPanel.getTopicName();
        tabbedPanel.addTab(this);
        ((TopicScope) findAncestor(TopicScope.class)).addEmitter(this);

        super.end(writer);
    }

    public void evaluateExtraParams() {
        super.evaluateExtraParams();

        if (tabName != null) {
            addParameter("tabName", findString(tabName));
        }

        if (subscribeTopicName != null) {
            addParameter("subscribeTopicName", findString(subscribeTopicName));
        }
    }

    public String getTabName() {
        return tabName;
    }

    public void emittJavascript(Writer writer) {
    }

    public String getComponentName() {
        return COMPONENT_NAME;
    }

    public void emittInstanceConfigurationJavascript(Writer writer) {
        StringBuffer sb = new StringBuffer();

        // create the remote div object
//        sb.append("var remotediv_").append(id);
//        sb.append(" = new RemoteUpdateComponent( '");
//        sb.append(getParameters().get("href")).append("', 'tab_contents_update_").append(id).append("', '");
        //sb.append(getReloadingText()).append("', '");
//        sb.append(getParameters().get("errorText")).append("', '").append(getParameters().get("showErrorTransportText")).append("' );\n");


        sb.append("var tabpanelc_").append(id).append(" = new TabContent( \"").append(id).append("\", true );\n");
        sb.append("dojo.event.topic.subscribe( \"").append(subscribeTopicName).append("\"");
        sb.append(", tabpanelc_").append(id).append(", ");
        sb.append("\"updateVisibility\"").append(" );");
        sb.append("\n");

        // subscribe to the tab selection topics
//        sb.append("var tabpanel_").append(id).append(" = document.getElementById( \"tab_contents_update_").append(id).append("\" );\n");
//        sb.append("dojo.event.topic.subscribe( \"").append(subscribeTopicName).append("\"");
//        sb.append(", tabpanel_").append(id).append(", ");
//        sb.append("\"bind\"").append(" );");
//        sb.append("\n");

//        sb.append("dojo.event.topic.subscribe( \"").append(subscribeTopicName).append("\"");
//        sb.append(", tab_contents_update_").append(id).append(", ");
//        sb.append("\"bind\"").append(" );");
//        sb.append("\n");

        // code to update the panel when it is selected
//        tab_contents_update_ryh21
//        sb.append("dojo.event.topic.subscribe( \"").append(subscribeTopicName).append("\"");
//        sb.append(", remotediv_").append(id).append(", ");
//        sb.append("\"tabbedElementIdRefresh\"").append(" );");
//        sb.append("\n");

        try {
            writer.write(sb.toString() + "\n");
        } catch (IOException e) {
            LOG.error("Error writting JS to pageContext.out", e);
        }
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public void setSubscribeTopicName(String subscribeTopicName) {
        this.subscribeTopicName = subscribeTopicName;
    }
}
