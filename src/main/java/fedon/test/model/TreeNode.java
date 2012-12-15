package fedon.test.model;

import java.util.Collection;

/**
 * @author Dmytro Fedonin
 *
 */
public class TreeNode {
    private int id;
    private String name;
    private Data data;
    private Collection<TreeNode> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Collection<TreeNode> getChildren() {
        return children;
    }

    public String toString() {
        return name;
    }

    public void setChildren(Collection<TreeNode> children) {
        this.children = children;
    }

    public class Data {
        String band;
        String relation;

        public String getBand() {
            return band;
        }

        public void setBand(String band) {
            this.band = band;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }
    }
}
