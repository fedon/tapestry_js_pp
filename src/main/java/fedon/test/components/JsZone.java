package fedon.test.components;

import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

public class JsZone extends Zone {
    private String js;
    @Inject
    private JavaScriptSupport javaScriptSupport;

    @AfterRender
    void afterRender() {
        javaScriptSupport.addScript(js);
    }

    public String getJs() {
        return js;
    }

    public void setJs(String js) {
        this.js = js;
    }
}
