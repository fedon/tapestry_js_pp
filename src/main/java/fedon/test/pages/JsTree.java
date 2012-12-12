package fedon.test.pages;

import java.io.IOException;

import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fedon.test.dao.TreeDao;

/**
 * @author Dmytro Fedonin
 *
 */
@Import(library = { "context:js/jit-yc.js", "context:js/example1.js" }, stylesheet = { "context:css/Hypertree.css", "context:css/base.css" })
public class JsTree {
    static final Logger log = LoggerFactory.getLogger(JsTree.class);

    @Inject
    TreeDao treeDao;
    @InjectComponent
    private Zone zone;
    @Inject
    private JavaScriptSupport javaScriptSupport;

    // private String tree;

    Object onSuccess() {
        return zone.getBody();
    }

    @AfterRender
    public void afterRender() throws IOException {
        javaScriptSupport.addScript("init(%s);", treeDao.getStaticTree());
    }
}
