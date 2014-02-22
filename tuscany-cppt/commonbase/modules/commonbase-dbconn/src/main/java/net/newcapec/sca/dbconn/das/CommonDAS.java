/**
 * 
 */
package net.newcapec.sca.dbconn.das;

import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.util.DASFactory;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.DAS;
import org.oasisopen.sca.annotation.Reference;

/**
 * @author Administrator
 *
 */
public abstract class CommonDAS {
    
    private static final Logger commonDASLogger = Logger.getLogger(CommonDAS.class);

    private DefDBConnService defDBConnService;

    @Reference(name = "defDBConnService", required = true)
    public void setDefDBConnService(DefDBConnService defDBConnService) {
        this.defDBConnService = defDBConnService;
    }

    // 默认的功能模块das配置文件
    private static String DAS_CONFIG = "/dbconn.xml";

    public DAS getDAS() {
        DAS das = null;
        try {
            String path = "";
            path = this.getClass().getResource(DAS_CONFIG).getPath();
            das = DASFactory.Factory.createDAS(defDBConnService.getDefDBConn(), path);
        } catch (Exception e) {
            commonDASLogger.error(e.getMessage());
        }
        return das;
    }
}
