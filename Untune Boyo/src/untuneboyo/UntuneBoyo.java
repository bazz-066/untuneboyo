/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package untuneboyo;

import java.util.Vector;
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
public class UntuneBoyo extends MIDlet implements CommandListener {

    private boolean midletPaused = false;
    private NetworkInfoConnector noc;
    private int state;
        
    private static final int ALL = 0, SOURCE = 1, DEST = 2;
    
    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private java.util.Hashtable __previousDisplayables = new java.util.Hashtable();
    private List listPilihan;
    private Form frmCariArea;
    private TextField tfCariArea;
    private Map mainMap;
    private List listHasilCari;
    private Form frmCariRute;
    private TextField tfTujuan;
    private TextField tfAsal;
    private Spacer spacer;
    private List listHasilCariRute;
    private WaitScreen loadingScreen;
    private List listDetailRute;
    private WaitScreen networkInfoScreen;
    private Form frmNetworkInfo;
    private Command findCommand;
    private Command cariAreaKembaliCommand;
    private Command findAreaCommand;
    private Command backCommand;
    private Command cariRuteCommand;
    private Command cariTujuanCommand;
    private Command cariAsalCommand;
    private Command backToMapCommand;
    private Command showInMapCommand;
    private Command cariDariCommand;
    private Command cariKeCommand;
    private Command posisiAsalCommand;
    private Command posisiTujCommand;
    private SimpleCancellableTask loadingDataTask;
    private SimpleCancellableTask task;
    private Image imgSandClock;
    //</editor-fold>//GEN-END:|fields|0|

    /**
     * The UntuneBoyo constructor.
     */
    public UntuneBoyo() {
        this.noc = new NetworkInfoConnector(this);
        this.state = UntuneBoyo.ALL;
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
        switchDisplayable(null, getLoadingScreen());//GEN-LINE:|3-startMIDlet|1|3-postAction
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
                this.getListHasilCari().deleteAll();
                Vector searchResult = StopPoint.Search(this.tfCariArea.getString());
                for(int i=0; i<searchResult.size(); i++)
                {
                    StringBuffer sbHasil = new StringBuffer();
                    sbHasil.append(((StopPoint)searchResult.elementAt(i)).getParent().getNama());
                    sbHasil.append("-");
                    sbHasil.append(((StopPoint)searchResult.elementAt(i)).getNama());

                    listHasilCari.append(sbHasil.toString(), null);
                }
            }//GEN-BEGIN:|7-commandAction|5|90-preAction
        } else if (displayable == frmCariRute) {
            if (command == cariAsalCommand) {//GEN-END:|7-commandAction|5|90-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmCariArea());//GEN-LINE:|7-commandAction|6|90-postAction
                // write post-action user code here
                this.state = UntuneBoyo.SOURCE;
            } else if (command == cariRuteCommand) {//GEN-LINE:|7-commandAction|7|84-preAction
                // write pre-action user code here
                switchDisplayable(null, getListHasilCariRute());//GEN-LINE:|7-commandAction|8|84-postAction
                // write post-action user code here
            } else if (command == cariTujuanCommand) {//GEN-LINE:|7-commandAction|9|93-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmCariArea());//GEN-LINE:|7-commandAction|10|93-postAction
                // write post-action user code here
                this.state = UntuneBoyo.DEST;
            } else if (command == posisiAsalCommand) {//GEN-LINE:|7-commandAction|11|136-preAction
                // write pre-action user code here
                switchDisplayable(null, getNetworkInfoScreen());//GEN-LINE:|7-commandAction|12|136-postAction
                // write post-action user code here
            } else if (command == posisiTujCommand) {//GEN-LINE:|7-commandAction|13|138-preAction
                // write pre-action user code here
                switchDisplayable(null, getNetworkInfoScreen());//GEN-LINE:|7-commandAction|14|138-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|15|109-preAction
        } else if (displayable == listDetailRute) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|15|109-preAction
                // write pre-action user code here
                listDetailRuteAction();//GEN-LINE:|7-commandAction|16|109-postAction
                // write post-action user code here
            } else if (command == backToMapCommand) {//GEN-LINE:|7-commandAction|17|116-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|18|116-postAction
                // write post-action user code here
            } else if (command == showInMapCommand) {//GEN-LINE:|7-commandAction|19|112-preAction
                // write pre-action user code here
                switchDisplayable(null, getMainMap());//GEN-LINE:|7-commandAction|20|112-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|21|52-preAction
        } else if (displayable == listHasilCari) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|21|52-preAction
                // write pre-action user code here
                listHasilCariAction();//GEN-LINE:|7-commandAction|22|52-postAction
                // write post-action user code here
            } else if (command == backCommand) {//GEN-LINE:|7-commandAction|23|56-preAction
                // write pre-action user code here
                switchToPreviousDisplayable();//GEN-LINE:|7-commandAction|24|56-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|25|69-preAction
        } else if (displayable == listHasilCariRute) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|25|69-preAction
                // write pre-action user code here
                listHasilCariRuteAction();//GEN-LINE:|7-commandAction|26|69-postAction
                // write post-action user code here
            } else if (command == backToMapCommand) {//GEN-LINE:|7-commandAction|27|103-preAction
                // write pre-action user code here
                switchDisplayable(null, getMainMap());//GEN-LINE:|7-commandAction|28|103-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|29|129-preAction
        } else if (displayable == listPilihan) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|29|129-preAction
                // write pre-action user code here
                listPilihanAction();//GEN-LINE:|7-commandAction|30|129-postAction
                // write post-action user code here
            } else if (command == backCommand) {//GEN-LINE:|7-commandAction|31|131-preAction
                // write pre-action user code here
                switchDisplayable(null, getListHasilCari());//GEN-LINE:|7-commandAction|32|131-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|33|75-preAction
        } else if (displayable == loadingScreen) {
            if (command == WaitScreen.FAILURE_COMMAND) {//GEN-END:|7-commandAction|33|75-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|34|75-postAction
                // write post-action user code here
            } else if (command == WaitScreen.SUCCESS_COMMAND) {//GEN-LINE:|7-commandAction|35|74-preAction
                // write pre-action user code here
                switchDisplayable(null, getMainMap());//GEN-LINE:|7-commandAction|36|74-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|37|45-preAction
        } else if (displayable == mainMap) {
            if (command == findAreaCommand) {//GEN-END:|7-commandAction|37|45-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmCariArea());//GEN-LINE:|7-commandAction|38|45-postAction
                // write post-action user code here
                this.state = UntuneBoyo.ALL;
            }//GEN-BEGIN:|7-commandAction|39|141-preAction
        } else if (displayable == networkInfoScreen) {
            if (command == WaitScreen.FAILURE_COMMAND) {//GEN-END:|7-commandAction|39|141-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmCariRute());//GEN-LINE:|7-commandAction|40|141-postAction
                // write post-action user code here
            } else if (command == WaitScreen.SUCCESS_COMMAND) {//GEN-LINE:|7-commandAction|41|140-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmNetworkInfo());//GEN-LINE:|7-commandAction|42|140-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|43|7-postCommandAction
        }//GEN-END:|7-commandAction|43|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|44|
    //</editor-fold>//GEN-END:|7-commandAction|44|

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
            frmCariArea = new Form("Cari Tempat", new Item[] { getTfCariArea() });//GEN-BEGIN:|34-getter|1|34-postInit
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

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: listHasilCariAction ">//GEN-BEGIN:|50-action|0|50-preAction
    /**
     * Performs an action assigned to the selected list element in the listHasilCari component.
     */
    public void listHasilCariAction() {//GEN-END:|50-action|0|50-preAction
        // enter pre-action user code here
        String __selectedString = getListHasilCari().getString(getListHasilCari().getSelectedIndex());//GEN-LINE:|50-action|1|50-postAction
        // enter post-action user code here
        if(this.state == UntuneBoyo.ALL)
        {
            this.switchDisplayable(null, this.getListPilihan());
            this.listPilihan.set(0, "Cari rute dari " + __selectedString, null);
            this.listPilihan.set(1, "Cari rute ke " + __selectedString, null);
        }
        else if(this.state == UntuneBoyo.SOURCE)
        {
            String asal = this.getNamaStopPoint(__selectedString);
            this.getTfAsal().setString(asal);
            this.switchDisplayable(null, getFrmCariRute());
            this.state = UntuneBoyo.ALL;
        }
        else if(this.state == UntuneBoyo.DEST)
        {
            String tujuan = this.getNamaStopPoint(__selectedString);
            this.getTfTujuan().setString(tujuan);
            this.switchDisplayable(null, getFrmCariRute());
            this.state = UntuneBoyo.ALL;
        }
    }//GEN-BEGIN:|50-action|2|
    //</editor-fold>//GEN-END:|50-action|2|

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
            frmCariRute.addCommand(getPosisiAsalCommand());
            frmCariRute.addCommand(getPosisiTujCommand());
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
            loadingScreen.setImage(getImgSandClock());
            loadingScreen.setText("Loading Data ...");
            loadingScreen.setTask(getLoadingDataTask());//GEN-END:|71-getter|1|71-postInit
            // write post-init user code here
        }//GEN-BEGIN:|71-getter|2|
        return loadingScreen;
    }
    //</editor-fold>//GEN-END:|71-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: loadingDataTask ">//GEN-BEGIN:|76-getter|0|76-preInit
    /**
     * Returns an initiliazed instance of loadingDataTask component.
     * @return the initialized component instance
     */
    public SimpleCancellableTask getLoadingDataTask() {
        if (loadingDataTask == null) {//GEN-END:|76-getter|0|76-preInit
            // write pre-init user code here
            loadingDataTask = new SimpleCancellableTask();//GEN-BEGIN:|76-getter|1|76-execute
            loadingDataTask.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|76-getter|1|76-execute
                    // write task-execution user code here
                    int retval = Jalan.LoadJalanFromFile();
                    if(retval == -1) { throw new Exception("Gagal load data jalan"); }
                    retval = StopPoint.LoadStopPointFromFile();
                    if(retval == -1) { throw new Exception("Gagal load data tempat berhenti"); }
                    retval = Route.LoadRouteFromFile();
                    if(retval == -1) { throw new Exception("Gagal load data rute"); }
                    CommonStops.LoadCommonStopsCollection();
                    ConnectivityMatriks.GenerateMatriks();                    
                }//GEN-BEGIN:|76-getter|2|76-postInit
            });//GEN-END:|76-getter|2|76-postInit
            // write post-init user code here
        }//GEN-BEGIN:|76-getter|3|
        return loadingDataTask;
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
            tfTujuan = new TextField("Tujuan :", null, 32, TextField.ANY | TextField.UNEDITABLE);//GEN-LINE:|87-getter|1|87-postInit
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

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: tfCariArea ">//GEN-BEGIN:|95-getter|0|95-preInit
    /**
     * Returns an initiliazed instance of tfCariArea component.
     * @return the initialized component instance
     */
    public TextField getTfCariArea() {
        if (tfCariArea == null) {//GEN-END:|95-getter|0|95-preInit
            // write pre-init user code here
            tfCariArea = new TextField("Masukkan Kata Kunci / Nama Jalan :", null, 50, TextField.ANY);//GEN-LINE:|95-getter|1|95-postInit
            // write post-init user code here
        }//GEN-BEGIN:|95-getter|2|
        return tfCariArea;
    }
    //</editor-fold>//GEN-END:|95-getter|2|

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

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cariDariCommand ">//GEN-BEGIN:|119-getter|0|119-preInit
    /**
     * Returns an initiliazed instance of cariDariCommand component.
     * @return the initialized component instance
     */
    public Command getCariDariCommand() {
        if (cariDariCommand == null) {//GEN-END:|119-getter|0|119-preInit
            // write pre-init user code here
            cariDariCommand = new Command("Cari Rute Dari ...", Command.ITEM, 0);//GEN-LINE:|119-getter|1|119-postInit
            // write post-init user code here
        }//GEN-BEGIN:|119-getter|2|
        return cariDariCommand;
    }
    //</editor-fold>//GEN-END:|119-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cariKeCommand ">//GEN-BEGIN:|120-getter|0|120-preInit
    /**
     * Returns an initiliazed instance of cariKeCommand component.
     * @return the initialized component instance
     */
    public Command getCariKeCommand() {
        if (cariKeCommand == null) {//GEN-END:|120-getter|0|120-preInit
            // write pre-init user code here
            cariKeCommand = new Command("Cari Rute Ke ...", Command.ITEM, 0);//GEN-LINE:|120-getter|1|120-postInit
            // write post-init user code here
        }//GEN-BEGIN:|120-getter|2|
        return cariKeCommand;
    }
    //</editor-fold>//GEN-END:|120-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: listHasilCari ">//GEN-BEGIN:|50-getter|0|50-preInit
    /**
     * Returns an initiliazed instance of listHasilCari component.
     * @return the initialized component instance
     */
    public List getListHasilCari() {
        if (listHasilCari == null) {//GEN-END:|50-getter|0|50-preInit
            // write pre-init user code here
            listHasilCari = new List("Hasil Pencarian", Choice.IMPLICIT);//GEN-BEGIN:|50-getter|1|50-postInit
            listHasilCari.addCommand(getBackCommand());
            listHasilCari.setCommandListener(this);
            listHasilCari.setFitPolicy(Choice.TEXT_WRAP_DEFAULT);
            listHasilCari.setSelectedFlags(new boolean[] {  });//GEN-END:|50-getter|1|50-postInit
            // write post-init user code here
        }//GEN-BEGIN:|50-getter|2|
        return listHasilCari;
    }
    //</editor-fold>//GEN-END:|50-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: listPilihan ">//GEN-BEGIN:|128-getter|0|128-preInit
    /**
     * Returns an initiliazed instance of listPilihan component.
     * @return the initialized component instance
     */
    public List getListPilihan() {
        if (listPilihan == null) {//GEN-END:|128-getter|0|128-preInit
            // write pre-init user code here
            listPilihan = new List("Pilih salah satu", Choice.IMPLICIT);//GEN-BEGIN:|128-getter|1|128-postInit
            listPilihan.append("Cari Rute Dari", null);
            listPilihan.append("Cari Rute Ke", null);
            listPilihan.addCommand(getBackCommand());
            listPilihan.setCommandListener(this);
            listPilihan.setSelectedFlags(new boolean[] { true, false });//GEN-END:|128-getter|1|128-postInit
            // write post-init user code here
        }//GEN-BEGIN:|128-getter|2|
        return listPilihan;
    }
    //</editor-fold>//GEN-END:|128-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: listPilihanAction ">//GEN-BEGIN:|128-action|0|128-preAction
    /**
     * Performs an action assigned to the selected list element in the listPilihan component.
     */
    public void listPilihanAction() {//GEN-END:|128-action|0|128-preAction
        // enter pre-action user code here
        switch (getListPilihan().getSelectedIndex()) {//GEN-BEGIN:|128-action|1|133-preAction
            case 0://GEN-END:|128-action|1|133-preAction
                // write pre-action user code here
//GEN-LINE:|128-action|2|133-postAction
                // write post-action user code here
                String asal = this.getNamaStopPoint(this.getListPilihan().getString(0));
                this.getTfAsal().setString(asal);
                this.switchDisplayable(null, getFrmCariRute());
                break;//GEN-BEGIN:|128-action|3|134-preAction
            case 1://GEN-END:|128-action|3|134-preAction
                // write pre-action user code here
//GEN-LINE:|128-action|4|134-postAction
                // write post-action user code here
                String tujuan = this.getNamaStopPoint(this.getListPilihan().getString(1));
                this.getTfTujuan().setString(tujuan);
                this.switchDisplayable(null, getFrmCariRute());
                break;//GEN-BEGIN:|128-action|5|128-postAction
        }//GEN-END:|128-action|5|128-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|128-action|6|
    //</editor-fold>//GEN-END:|128-action|6|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: networkInfoScreen ">//GEN-BEGIN:|139-getter|0|139-preInit
    /**
     * Returns an initiliazed instance of networkInfoScreen component.
     * @return the initialized component instance
     */
    public WaitScreen getNetworkInfoScreen() {
        if (networkInfoScreen == null) {//GEN-END:|139-getter|0|139-preInit
            // write pre-init user code here
            networkInfoScreen = new WaitScreen(getDisplay());//GEN-BEGIN:|139-getter|1|139-postInit
            networkInfoScreen.setTitle("Mengambil Data Cell ID");
            networkInfoScreen.setCommandListener(this);
            networkInfoScreen.setImage(getImgSandClock());
            networkInfoScreen.setText("Ambil Data Jaringan...");
            networkInfoScreen.setTask(getTask());//GEN-END:|139-getter|1|139-postInit
            // write post-init user code here
        }//GEN-BEGIN:|139-getter|2|
        return networkInfoScreen;
    }
    //</editor-fold>//GEN-END:|139-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: posisiAsalCommand ">//GEN-BEGIN:|135-getter|0|135-preInit
    /**
     * Returns an initiliazed instance of posisiAsalCommand component.
     * @return the initialized component instance
     */
    public Command getPosisiAsalCommand() {
        if (posisiAsalCommand == null) {//GEN-END:|135-getter|0|135-preInit
            // write pre-init user code here
            posisiAsalCommand = new Command("Item", Command.ITEM, 0);//GEN-LINE:|135-getter|1|135-postInit
            // write post-init user code here
        }//GEN-BEGIN:|135-getter|2|
        return posisiAsalCommand;
    }
    //</editor-fold>//GEN-END:|135-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: posisiTujCommand ">//GEN-BEGIN:|137-getter|0|137-preInit
    /**
     * Returns an initiliazed instance of posisiTujCommand component.
     * @return the initialized component instance
     */
    public Command getPosisiTujCommand() {
        if (posisiTujCommand == null) {//GEN-END:|137-getter|0|137-preInit
            // write pre-init user code here
            posisiTujCommand = new Command("Item", Command.ITEM, 0);//GEN-LINE:|137-getter|1|137-postInit
            // write post-init user code here
        }//GEN-BEGIN:|137-getter|2|
        return posisiTujCommand;
    }
    //</editor-fold>//GEN-END:|137-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: task ">//GEN-BEGIN:|142-getter|0|142-preInit
    /**
     * Returns an initiliazed instance of task component.
     * @return the initialized component instance
     */
    public SimpleCancellableTask getTask() {
        if (task == null) {//GEN-END:|142-getter|0|142-preInit
            // write pre-init user code here
            task = new SimpleCancellableTask();//GEN-BEGIN:|142-getter|1|142-execute
            task.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|142-getter|1|142-execute
                    // write task-execution user code here
                }//GEN-BEGIN:|142-getter|2|142-postInit
            });//GEN-END:|142-getter|2|142-postInit
            // write post-init user code here
        }//GEN-BEGIN:|142-getter|3|
        return task;
    }
    //</editor-fold>//GEN-END:|142-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: imgSandClock ">//GEN-BEGIN:|145-getter|0|145-preInit
    /**
     * Returns an initiliazed instance of imgSandClock component.
     * @return the initialized component instance
     */
    public Image getImgSandClock() {
        if (imgSandClock == null) {//GEN-END:|145-getter|0|145-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|145-getter|1|145-@java.io.IOException
                imgSandClock = Image.createImage("/untuneboyo/resource/sand_clock.gif");
            } catch (java.io.IOException e) {//GEN-END:|145-getter|1|145-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|145-getter|2|145-postInit
            // write post-init user code here
        }//GEN-BEGIN:|145-getter|3|
        return imgSandClock;
    }
    //</editor-fold>//GEN-END:|145-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmNetworkInfo ">//GEN-BEGIN:|146-getter|0|146-preInit
    /**
     * Returns an initiliazed instance of frmNetworkInfo component.
     * @return the initialized component instance
     */
    public Form getFrmNetworkInfo() {
        if (frmNetworkInfo == null) {//GEN-END:|146-getter|0|146-preInit
            // write pre-init user code here
            frmNetworkInfo = new Form("Network Info");//GEN-LINE:|146-getter|1|146-postInit
            // write post-init user code here
        }//GEN-BEGIN:|146-getter|2|
        return frmNetworkInfo;
    }
    //</editor-fold>//GEN-END:|146-getter|2|

    private String getNamaStopPoint(String key)
    {
        String[] tmp = NetworkInfoConnector.split(key, '-');
        return tmp[tmp.length - 1];
    }



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
