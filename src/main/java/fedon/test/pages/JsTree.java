package fedon.test.pages;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fedon.test.components.JsZone;
import fedon.test.dao.TreeDao;
import fedon.test.model.TreeNode;

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
    private JsZone zone;
    @Property
    private Integer parentId;
    @Property
    Map<Integer, TreeNode> parentSelectModel = Collections.emptyMap();
    // private SelectModel parentSelectModel;
    @Property
    private String newNodeName;
    @Property
    private String newDataBand;
    @Property
    private String newDataRelation;

    Object onActionFromDemo() throws IOException {
        zone.setJs(String.format("init(%s);", treeDao.getStaticTree()));
        return zone;
    }

    Object onActionFromInit() {
        treeDao.initTree();
        parentSelectModel = treeDao.getNodeModel();
        log.debug("--- select model size: " + parentSelectModel.size());
        zone.setJs(String.format("init(%s);", treeDao.getDynamicTree()));
        return zone;
    }

    Object onSuccess() {
        TreeNode newNode = new TreeNode();
        newNode.setName(newNodeName);
        newNode.setData(newNode.new Data());
        newNode.getData().setBand(newDataBand);
        newNode.getData().setRelation(newDataRelation);

        treeDao.addNode(parentId, newNode);
        parentSelectModel = treeDao.getNodeModel();
        log.debug("--- select model size: " + parentSelectModel.size());
        zone.setJs(String.format("init(%s);", treeDao.getDynamicTree()));
        return zone;
    }
}
