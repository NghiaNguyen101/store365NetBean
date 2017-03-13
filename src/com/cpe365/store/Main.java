package com.cpe365.store; /**
 * Created by Owner on 2/26/2017.
 */

import java.sql.SQLException;
import com.cpe365.store.GUI.ItemList;
import static java.lang.System.exit;
import warehouse.ManageItem;

public class Main {
    public static void main(String [] args) throws SQLException
    {   
        String usage = "Usage: java -jar store365NetBean.jar [--c] [--m] [--u]\n" +
                       "Default is Customer GUI\n" +
                       "--c: customer GUI\n" +
                       "--m: manager GUI\n" + 
                       "--u: usage";
        if(args.length == 1 && args[0].equals("--u")) {
            System.out.println(usage);
            exit(0);
        } else if(args.length == 1 && args[0].equals("--m")) {
            ManageItem managerGUI = new ManageItem();
            managerGUI.setVisible(true);
        } else if(args.length == 0 || args.length == 1 && args[0].equals("--c")) {
            ItemList customerGUI = new ItemList();
            customerGUI.setVisible(true); 
        } else {
            System.err.println("Error: wrong option");
            System.err.println(usage);
            exit(1);
        } 
    }
}
