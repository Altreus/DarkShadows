package advtech.ds.lib;

/**
 * Reference
 * 
 * General purpose library to contain mod related constants
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class Reference {

    /* Debug Mode On-Off */
    public static final boolean DEBUG_MODE = false;

    /* General Mod related constants */
    public static final String MOD_ID = "DS";
    public static final String MOD_NAME = "Darkened Souls";
    public static final String VERSION = "@VERSION@";
    public static final String CHANNEL_NAME = MOD_ID;
    public static final int SECOND_IN_TICKS = 20;
    public static final int SHIFTED_ID_RANGE_CORRECTION = 256;
    public static final String SERVER_PROXY_CLASS = "advtech.ds.core.proxy.CommonProxy";
    public static final String CLIENT_PROXY_CLASS = "advtech.ds.core.proxy.ClientProxy";

}