/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package untuneboyo;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import org.netbeans.microedition.lcdui.SplashScreen;
import org.netbeans.microedition.lcdui.WaitScreen;
import org.netbeans.microedition.util.SimpleCancellableTask;
import untuneboyo.connection.*;
import untuneboyo.pathplanning.AdjacencyMatriks;
import untuneboyo.pathplanning.CommonStops;
import untuneboyo.pathplanning.ConnectivityMatriks;
import untuneboyo.pathplanning.Jalan;
import untuneboyo.pathplanning.Route;
import untuneboyo.pathplanning.StopPoint;

/**
 * @author baskoro
 */
public class MainMidLet extends MIDlet implements CommandListener {

    private boolean midletPaused = false;
    private NetworkInfoConnector noc;
    
    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private java.util.Hashtable __previousDisplayables = new java.util.Hashtable();
    private Form frmCariArea;
    private TextField textField;
    private ChoiceGroup cgHasilCariArea;
    private Map mainMap;
    private List listHasilCari;
    private Form frmCariRute;
    private TextField tfAsal;
    private TextField tfTujuan;
    private Spacer spacer;
    private List listHasilCariRute;
    private WaitScreen loadingScreen;
    private SplashScreen splashScreen;
    private List listDetailRute;
    private Command findCommand;
    private Command findAreaCommand;
    private Command cariAreaKembaliCommand;
    private Command backCommand;
    private Command cariRuteCommand;
    private Command cariAsalCommand;
    private Command cariTujuanCommand;
    private Command backToMapCommand;
    private Command showInMapCommand;
    private SimpleCancellableTask task;
    //</editor-fold>//GEN-END:|fields|0|

    /**
     * The MainMidLet constructor.
     */
    public MainMidLet() {
        this.noc = new NetworkInfoConnector(this);
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    /**
     * Switches a display to previous displayable of the current displayable.
     * The <code>display</code> instance is obtain from the <code>getDisplay</code> method.
     */
    private void switchToPreviousDisplayable() {
        Displayable __currentDisplayable = getDisplay().getCurrent();
        if (__currentDisplayable != null) {
            Displayable __nextDisplayable = (Displayable) __previousDisplayables.get(__currentDisplayable);
            if (__nextDisplayable != null) {
                switchDisplayable(null, __nextDisplayable);
            }
        }
    }
    //</editor-fold>//GEN-END:|methods|0|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, getSplashScreen());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        Displayable __currentDisplayable = display.getCurrent();
        if (__currentDisplayable != null  &&  nextDisplayable != null) {
            __previousDisplayables.put(nextDisplayable, __currentDisplayable);
        }
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == frmCariArea) {//GEN-BEGIN:|7-commandAction|1|47-preAction
            if (command == cariAreaKembaliCommand) {//GEN-END:|7-commandAction|1|47-preAction
                // write pre-action user code here
                switchToPreviousDisplayable();//GEN-LINE:|7-commandAction|2|47-postAction
                // write post-action user code here
            } else if (command == findCommand) {//GEN-LINE:|7-commandAction|3|36-preAction
                // write pre-action user code here
                switchDisplayable(null, getListHasilCari());//GEN-LINE:|7-commandAction|4|36-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|5|90-preAction
        } else if (displayable == frmCariRute) {
            if (command == cariAsalCommand) {//GEN-END:|7-commandAction|5|90-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmCariArea());//GEN-LINE:|7-commandAction|6|90-postAction
                // write post-action user code here
            } else if (command == cariRuteCommand) {//GEN-LINE:|7-commandAction|7|84-preAction
                // write pre-action user code here
                switchDisplayable(null, getListHasilCariRute());//GEN-LINE:|7-commandAction|8|84-postAction
                // write post-action user code here
            } else if (command == cariTujuanCommand) {//GEN-LINE:|7-commandAction|9|93-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmCariArea());//GEN-LINE:|7-commandAction|10|93-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|11|109-preAction
        } else if (displayable == listDetailRute) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|11|109-preAction
                // write pre-action user code here
                listDetailRuteAction();//GEN-LINE:|7-commandAction|12|109-postAction
                // write post-action user code here
            } else if (command == backToMapCommand) {//GEN-LINE:|7-commandAction|13|116-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|14|116-postAction
                // write post-action user code here
            } else if (command == showInMapCommand) {//GEN-LINE:|7-commandAction|15|112-preAction
                // write pre-action user code here
                switchDisplayable(null, getMainMap());//GEN-LINE:|7-commandAction|16|112-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|17|52-preAction
        } else if (displayable == listHasilCari) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|17|52-preAction
                // write pre-action user code here
                listHasilCariAction();//GEN-LINE:|7-commandAction|18|52-postAction
                // write post-action user code here
            } else if (command == backCommand) {//GEN-LINE:|7-commandAction|19|56-preAction
                // write pre-action user code here
                switchToPreviousDisplayable();//GEN-LINE:|7-commandAction|20|56-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|21|69-preAction
        } else if (displayable == listHasilCariRute) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|21|69-preAction
                // write pre-action user code here
                listHasilCariRuteAction();//GEN-LINE:|7-commandAction|22|69-postAction
                // write post-action user code here
            } else if (command == backToMapCommand) {//GEN-LINE:|7-commandAction|23|103-preAction
                // write pre-action user code here
                switchDisplayable(null, getMainMap());//GEN-LINE:|7-commandAction|24|103-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|25|75-preAction
        } else if (displayable == loadingScreen) {
            if (command == WaitScreen.FAILURE_COMMAND) {//GEN-END:|7-commandAction|25|75-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|26|75-postAction
                // write post-action user code here
            } else if (command == WaitScreen.SUCCESS_COMMAND) {//GEN-LINE:|7-commandAction|27|74-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|28|74-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|29|45-preAction
        } else if (displayable == mainMap) {
            if (command == findAreaCommand) {//GEN-END:|7-commandAction|29|45-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmCariArea());//GEN-LINE:|7-commandAction|30|45-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|31|80-preAction
        } else if (displayable == splashScreen) {
            if (command == SplashScreen.DISMISS_COMMAND) {//GEN-END:|7-commandAction|31|80-preAction
                // write pre-action user code here
                switchDisplayable(null, getMainMap());//GEN-LINE:|7-commandAction|32|80-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|33|7-postCommandAction
        }//GEN-END:|7-commandAction|33|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|34|
    //</editor-fold>//GEN-END:|7-commandAction|34|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: mainMap ">//GEN-BEGIN:|32-getter|0|32-preInit
    /**
     * Returns an initiliazed instance of mainMap component.
     * @return the initialized component instance
     */
    public Map getMainMap() {
        if (mainMap == null) {//GEN-END:|32-getter|0|32-preInit
            // write pre-init user code here
            mainMap = new Map();//GEN-BEGIN:|32-getter|1|32-postInit
            mainMap.setTitle("Untuneboyo - Peta Surabaya");
            mainMap.addCommand(getFindAreaCommand());
            mainMap.setCommandListener(this);
            mainMap.setFullScreenMode(true);//GEN-END:|32-getter|1|32-postInit
            // write post-init user code here
        }//GEN-BEGIN:|32-getter|2|
        return mainMap;
    }
    //</editor-fold>//GEN-END:|32-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmCariArea ">//GEN-BEGIN:|34-getter|0|34-preInit
    /**
     * Returns an initiliazed instance of frmCariArea component.
     * @return the initialized component instance
     */
    public Form getFrmCariArea() {
        if (frmCariArea == null) {//GEN-END:|34-getter|0|34-preInit
            // write pre-init user code here
            frmCariArea = new Form("Cari Tempat", new Item[] { getTextField(), getCgHasilCariArea() });//GEN-BEGIN:|34-getter|1|34-postInit
            frmCariArea.addCommand(getFindCommand());
            frmCariArea.addCommand(getCariAreaKembaliCommand());
            frmCariArea.setCommandListener(this);//GEN-END:|34-getter|1|34-postInit
            // write post-init user code here
        }//GEN-BEGIN:|34-getter|2|
        return frmCariArea;
    }
    //</editor-fold>//GEN-END:|34-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: findCommand ">//GEN-BEGIN:|35-getter|0|35-preInit
    /**
     * Returns an initiliazed instance of findCommand component.
     * @return the initialized component instance
     */
    public Command getFindCommand() {
        if (findCommand == null) {//GEN-END:|35-getter|0|35-preInit
            // write pre-init user code here
            findCommand = new Command("Cari", Command.OK, 0);//GEN-LINE:|35-getter|1|35-postInit
            // write post-init user code here
        }//GEN-BEGIN:|35-getter|2|
        return findCommand;
    }
    //</editor-fold>//GEN-END:|35-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: findAreaCommand ">//GEN-BEGIN:|44-getter|0|44-preInit
    /**
     * Returns an initiliazed instance of findAreaCommand component.
     * @return the initialized component instance
     */
    public Command getFindAreaCommand() {
        if (findAreaCommand == null) {//GEN-END:|44-getter|0|44-preInit
            // write pre-init user code here
            findAreaCommand = new Command("Cari Tempat", Command.ITEM, 0);//GEN-LINE:|44-getter|1|44-postInit
            // write post-init user code here
        }//GEN-BEGIN:|44-getter|2|
        return findAreaCommand;
    }
    //</editor-fold>//GEN-END:|44-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: listHasilCari ">//GEN-BEGIN:|50-getter|0|50-preInit
    /**
     * Returns an initiliazed instance of listHasilCari component.
     * @return the initialized component instance
     */
    public List getListHasilCari() {
        if (listHasilCari == null) {//GEN-END:|50-getter|0|50-preInit
            // write pre-init user code here
            listHasilCari = new List("Hasil Pencarian", Choice.IMPLICIT);//GEN-BEGIN:|50-getter|1|50-postInit
            listHasilCari.append("Nama Tempat xxx", null);
            listHasilCari.addCommand(getBackCommand());
            listHasilCari.setCommandListener(this);
            listHasilCari.setFitPolicy(Choice.TEXT_WRAP_DEFAULT);
            listHasilCari.setSelectedFlags(new boolean[] { false });//GEN-END:|50-getter|1|50-postInit
            // write post-init user code here
        }//GEN-BEGIN:|50-getter|2|
        return listHasilCari;
    }
    //</editor-fold>//GEN-END:|50-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: listHasilCariAction ">//GEN-BEGIN:|50-action|0|50-preAction
    /**
     * Performs an action assigned to the selected list element in the listHasilCari component.
     */
    public void listHasilCariAction() {//GEN-END:|50-action|0|50-preAction
        // enter pre-action user code here
        String __selectedString = getListHasilCari().getString(getListHasilCari().getSelectedIndex());//GEN-BEGIN:|50-action|1|105-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("Nama Tempat xxx")) {//GEN-END:|50-action|1|105-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmCariRute());//GEN-LINE:|50-action|2|105-postAction
                // write post-action user code here
            }//GEN-BEGIN:|50-action|3|50-postAction
        }//GEN-END:|50-action|3|50-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|50-action|4|
    //</editor-fold>//GEN-END:|50-action|4|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cariAreaKembaliCommand ">//GEN-BEGIN:|46-getter|0|46-preInit
    /**
     * Returns an initiliazed instance of cariAreaKembaliCommand component.
     * @return the initialized component instance
     */
    public Command getCariAreaKembaliCommand() {
        if (cariAreaKembaliCommand == null) {//GEN-END:|46-getter|0|46-preInit
            // write pre-init user code here
            cariAreaKembaliCommand = new Command("Kembali", Command.BACK, 0);//GEN-LINE:|46-getter|1|46-postInit
            // write post-init user code here
        }//GEN-BEGIN:|46-getter|2|
        return cariAreaKembaliCommand;
    }
    //</editor-fold>//GEN-END:|46-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand ">//GEN-BEGIN:|55-getter|0|55-preInit
    /**
     * Returns an initiliazed instance of backCommand component.
     * @return the initialized component instance
     */
    public Command getBackCommand() {
        if (backCommand == null) {//GEN-END:|55-getter|0|55-preInit
            // write pre-init user code here
            backCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|55-getter|1|55-postInit
            // write post-init user code here
        }//GEN-BEGIN:|55-getter|2|
        return backCommand;
    }
    //</editor-fold>//GEN-END:|55-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmCariRute ">//GEN-BEGIN:|62-getter|0|62-preInit
    /**
     * Returns an initiliazed instance of frmCariRute component.
     * @return the initialized component instance
     */
    public Form getFrmCariRute() {
        if (frmCariRute == null) {//GEN-END:|62-getter|0|62-preInit
            // write pre-init user code here
            frmCariRute = new Form("Cari Rute Bemo", new Item[] { getTfAsal(), getSpacer(), getTfTujuan() });//GEN-BEGIN:|62-getter|1|62-postInit
            frmCariRute.addCommand(getCariAsalCommand());
            frmCariRute.addCommand(getCariTujuanCommand());
            frmCariRute.addCommand(getCariRuteCommand());
            frmCariRute.setCommandListener(this);//GEN-END:|62-getter|1|62-postInit
            // write post-init user code here
        }//GEN-BEGIN:|62-getter|2|
        return frmCariRute;
    }
    //</editor-fold>//GEN-END:|62-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: listHasilCariRute ">//GEN-BEGIN:|68-getter|0|68-preInit
    /**
     * Returns an initiliazed instance of listHasilCariRute component.
     * @return the initialized component instance
     */
    public List getListHasilCariRute() {
        if (listHasilCariRute == null) {//GEN-END:|68-getter|0|68-preInit
            // write pre-init user code here
            listHasilCariRute = new List("Hasil Pencarian Rute", Choice.IMPLICIT);//GEN-BEGIN:|68-getter|1|68-postInit
            listHasilCariRute.append("bemo X - bemo Y....", null);
            listHasilCariRute.addCommand(getBackToMapCommand());
            listHasilCariRute.setCommandListener(this);
            listHasilCariRute.setSelectedFlags(new boolean[] { false });//GEN-END:|68-getter|1|68-postInit
            // write post-init user code here
        }//GEN-BEGIN:|68-getter|2|
        return listHasilCariRute;
    }
    //</editor-fold>//GEN-END:|68-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: listHasilCariRuteAction ">//GEN-BEGIN:|68-action|0|68-preAction
    /**
     * Performs an action assigned to the selected list element in the listHasilCariRute component.
     */
    public void listHasilCariRuteAction() {//GEN-END:|68-action|0|68-preAction
        // enter pre-action user code here
        String __selectedString = getListHasilCariRute().getString(getListHasilCariRute().getSelectedIndex());//GEN-BEGIN:|68-action|1|113-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("bemo X - bemo Y....")) {//GEN-END:|68-action|1|113-preAction
                // write pre-action user code here
                switchDisplayable(null, getListDetailRute());//GEN-LINE:|68-action|2|113-postAction
                // write post-action user code here
            }//GEN-BEGIN:|68-action|3|68-postAction
        }//GEN-END:|68-action|3|68-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|68-action|4|
    //</editor-fold>//GEN-END:|68-action|4|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: loadingScreen ">//GEN-BEGIN:|71-getter|0|71-preInit
    /**
     * Returns an initiliazed instance of loadingScreen component.
     * @return the initialized component instance
     */
    public WaitScreen getLoadingScreen() {
        if (loadingScreen == null) {//GEN-END:|71-getter|0|71-preInit
            // write pre-init user code here
            loadingScreen = new WaitScreen(getDisplay());//GEN-BEGIN:|71-getter|1|71-postInit
            loadingScreen.setTitle("Loading...");
            loadingScreen.setCommandListener(this);
            loadingScreen.setTask(getTask());//GEN-END:|71-getter|1|71-postInit
            // write post-init user code here
        }//GEN-BEGIN:|71-getter|2|
        return loadingScreen;
    }
    //</editor-fold>//GEN-END:|71-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: task ">//GEN-BEGIN:|76-getter|0|76-preInit
    /**
     * Returns an initiliazed instance of task component.
     * @return the initialized component instance
     */
    public SimpleCancellableTask getTask() {
        if (task == null) {//GEN-END:|76-getter|0|76-preInit
            // write pre-init user code here
            task = new SimpleCancellableTask();//GEN-BEGIN:|76-getter|1|76-execute
            task.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|76-getter|1|76-execute
                    // write task-execution user code here
                }//GEN-BEGIN:|76-getter|2|76-postInit
            });//GEN-END:|76-getter|2|76-postInit
            // write post-init user code here
        }//GEN-BEGIN:|76-getter|3|
        return task;
    }
    //</editor-fold>//GEN-END:|76-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: tfAsal ">//GEN-BEGIN:|86-getter|0|86-preInit
    /**
     * Returns an initiliazed instance of tfAsal component.
     * @return the initialized component instance
     */
    public TextField getTfAsal() {
        if (tfAsal == null) {//GEN-END:|86-getter|0|86-preInit
            // write pre-init user code here
            tfAsal = new TextField("Asal :", null, 255, TextField.ANY | TextField.UNEDITABLE);//GEN-LINE:|86-getter|1|86-postInit
            // write post-init user code here
        }//GEN-BEGIN:|86-getter|2|
        return tfAsal;
    }
    //</editor-fold>//GEN-END:|86-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: tfTujuan ">//GEN-BEGIN:|87-getter|0|87-preInit
    /**
     * Returns an initiliazed instance of tfTujuan component.
     * @return the initialized component instance
     */
    public TextField getTfTujuan() {
        if (tfTujuan == null) {//GEN-END:|87-getter|0|87-preInit
            // write pre-init user code here
            tfTujuan = new TextField("Tujuan :", null, 32, TextField.ANY);//GEN-LINE:|87-getter|1|87-postInit
            // write post-init user code here
        }//GEN-BEGIN:|87-getter|2|
        return tfTujuan;
    }
    //</editor-fold>//GEN-END:|87-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: spacer ">//GEN-BEGIN:|88-getter|0|88-preInit
    /**
     * Returns an initiliazed instance of spacer component.
     * @return the initialized component instance
     */
    public Spacer getSpacer() {
        if (spacer == null) {//GEN-END:|88-getter|0|88-preInit
            // write pre-init user code here
            spacer = new Spacer(16, 10);//GEN-LINE:|88-getter|1|88-postInit
            // write post-init user code here
        }//GEN-BEGIN:|88-getter|2|
        return spacer;
    }
    //</editor-fold>//GEN-END:|88-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: splashScreen ">//GEN-BEGIN:|79-getter|0|79-preInit
    /**
     * Returns an initiliazed instance of splashScreen component.
     * @return the initialized component instance
     */
    public SplashScreen getSplashScreen() {
        if (splashScreen == null) {//GEN-END:|79-getter|0|79-preInit
            // write pre-init user code here
            splashScreen = new SplashScreen(getDisplay());//GEN-BEGIN:|79-getter|1|79-postInit
            splashScreen.setTitle("splashScreen");
            splashScreen.setCommandListener(this);//GEN-END:|79-getter|1|79-postInit
            // write post-init user code here
        }//GEN-BEGIN:|79-getter|2|
        return splashScreen;
    }
    //</editor-fold>//GEN-END:|79-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cariRuteCommand ">//GEN-BEGIN:|83-getter|0|83-preInit
    /**
     * Returns an initiliazed instance of cariRuteCommand component.
     * @return the initialized component instance
     */
    public Command getCariRuteCommand() {
        if (cariRuteCommand == null) {//GEN-END:|83-getter|0|83-preInit
            // write pre-init user code here
            cariRuteCommand = new Command("Ok", Command.OK, 0);//GEN-LINE:|83-getter|1|83-postInit
            // write post-init user code here
        }//GEN-BEGIN:|83-getter|2|
        return cariRuteCommand;
    }
    //</editor-fold>//GEN-END:|83-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField ">//GEN-BEGIN:|95-getter|0|95-preInit
    /**
     * Returns an initiliazed instance of textField component.
     * @return the initialized component instance
     */
    public TextField getTextField() {
        if (textField == null) {//GEN-END:|95-getter|0|95-preInit
            // write pre-init user code here
            textField = new TextField("Masukkan Kata Kunci / Nama Jalan :", null, 50, TextField.ANY);//GEN-LINE:|95-getter|1|95-postInit
            // write post-init user code here
        }//GEN-BEGIN:|95-getter|2|
        return textField;
    }
    //</editor-fold>//GEN-END:|95-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cgHasilCariArea ">//GEN-BEGIN:|96-getter|0|96-preInit
    /**
     * Returns an initiliazed instance of cgHasilCariArea component.
     * @return the initialized component instance
     */
    public ChoiceGroup getCgHasilCariArea() {
        if (cgHasilCariArea == null) {//GEN-END:|96-getter|0|96-preInit
            // write pre-init user code here
            cgHasilCariArea = new ChoiceGroup("Hasil Pencarian :", Choice.MULTIPLE);//GEN-LINE:|96-getter|1|96-postInit
            // write post-init user code here
        }//GEN-BEGIN:|96-getter|2|
        return cgHasilCariArea;
    }
    //</editor-fold>//GEN-END:|96-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cariAsalCommand ">//GEN-BEGIN:|89-getter|0|89-preInit
    /**
     * Returns an initiliazed instance of cariAsalCommand component.
     * @return the initialized component instance
     */
    public Command getCariAsalCommand() {
        if (cariAsalCommand == null) {//GEN-END:|89-getter|0|89-preInit
            // write pre-init user code here
            cariAsalCommand = new Command("Cari Tempat Asal", Command.ITEM, 0);//GEN-LINE:|89-getter|1|89-postInit
            // write post-init user code here
        }//GEN-BEGIN:|89-getter|2|
        return cariAsalCommand;
    }
    //</editor-fold>//GEN-END:|89-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cariTujuanCommand ">//GEN-BEGIN:|92-getter|0|92-preInit
    /**
     * Returns an initiliazed instance of cariTujuanCommand component.
     * @return the initialized component instance
     */
    public Command getCariTujuanCommand() {
        if (cariTujuanCommand == null) {//GEN-END:|92-getter|0|92-preInit
            // write pre-init user code here
            cariTujuanCommand = new Command("Cari Tempat Tujuan", Command.ITEM, 0);//GEN-LINE:|92-getter|1|92-postInit
            // write post-init user code here
        }//GEN-BEGIN:|92-getter|2|
        return cariTujuanCommand;
    }
    //</editor-fold>//GEN-END:|92-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backToMapCommand ">//GEN-BEGIN:|102-getter|0|102-preInit
    /**
     * Returns an initiliazed instance of backToMapCommand component.
     * @return the initialized component instance
     */
    public Command getBackToMapCommand() {
        if (backToMapCommand == null) {//GEN-END:|102-getter|0|102-preInit
            // write pre-init user code here
            backToMapCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|102-getter|1|102-postInit
            // write post-init user code here
        }//GEN-BEGIN:|102-getter|2|
        return backToMapCommand;
    }
    //</editor-fold>//GEN-END:|102-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: listDetailRute ">//GEN-BEGIN:|108-getter|0|108-preInit
    /**
     * Returns an initiliazed instance of listDetailRute component.
     * @return the initialized component instance
     */
    public List getListDetailRute() {
        if (listDetailRute == null) {//GEN-END:|108-getter|0|108-preInit
            // write pre-init user code here
            listDetailRute = new List("Jalur Dari xxx ke yyy", Choice.IMPLICIT);//GEN-BEGIN:|108-getter|1|108-postInit
            listDetailRute.addCommand(getShowInMapCommand());
            listDetailRute.addCommand(getBackToMapCommand());
            listDetailRute.setCommandListener(this);//GEN-END:|108-getter|1|108-postInit
            // write post-init user code here
        }//GEN-BEGIN:|108-getter|2|
        return listDetailRute;
    }
    //</editor-fold>//GEN-END:|108-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: listDetailRuteAction ">//GEN-BEGIN:|108-action|0|108-preAction
    /**
     * Performs an action assigned to the selected list element in the listDetailRute component.
     */
    public void listDetailRuteAction() {//GEN-END:|108-action|0|108-preAction
        // enter pre-action user code here
        String __selectedString = getListDetailRute().getString(getListDetailRute().getSelectedIndex());//GEN-LINE:|108-action|1|108-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|108-action|2|
    //</editor-fold>//GEN-END:|108-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: showInMapCommand ">//GEN-BEGIN:|111-getter|0|111-preInit
    /**
     * Returns an initiliazed instance of showInMapCommand component.
     * @return the initialized component instance
     */
    public Command getShowInMapCommand() {
        if (showInMapCommand == null) {//GEN-END:|111-getter|0|111-preInit
            // write pre-init user code here
            showInMapCommand = new Command("Tampilkan Rute di Peta", Command.ITEM, 0);//GEN-LINE:|111-getter|1|111-postInit
            // write post-init user code here
        }//GEN-BEGIN:|111-getter|2|
        return showInMapCommand;
    }
    //</editor-fold>//GEN-END:|111-getter|2|





    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay () {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable (null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet ();
        } else {
            initialize ();
            startMIDlet ();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }

}
