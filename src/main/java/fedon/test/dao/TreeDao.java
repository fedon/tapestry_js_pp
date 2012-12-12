package fedon.test.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @author Dmytro Fedonin
 *
 */
@Repository
public class TreeDao {
    Logger log = LoggerFactory.getLogger(TreeDao.class);

    private String tree;

    public String getStaticTree() throws IOException {
        if (tree == null) {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("static_tree.json");
            StringBuilder sb = new StringBuilder();
            char buf[] = new char[1024];
            Reader reader = new BufferedReader(new InputStreamReader(in));
            int result = 0;
            do {
                result = reader.read(buf);
                sb.append(buf, 0, result);
            } while (result == 1024);
            tree = sb.toString();
        }
        return tree;
    }
}
