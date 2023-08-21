package org.ars.example.design.pattern.structural;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author arsen.ibragimov
 *
 * Hide complex system under simple interface/class
 */
public class Facade1 {

    static Logger log = LogManager.getLogger( Facade1.class);

    public static enum ReportType {
        Report1, ReportN
    }

    static class Report1 {
        public Report1( String value1, String valueN) {
            super();
            this.value1 = value1;
            this.valueN = valueN;
        }

        String value1;
        String valueN;

        @Override
        public String toString() {
            return "Report1 [value1=" + value1 + ", valueN=" + valueN + "]";
        }
    }

    static class ReportN {
        public ReportN( String value1, String valueN) {
            super();
            this.value1 = value1;
            this.valueN = valueN;
        }

        String value1;
        String valueN;

        @Override
        public String toString() {
            return "ReportN [value1=" + value1 + ", valueN=" + valueN + "]";
        }
    }

    static class FacadeHelper {
        public void generateReport( ReportType reportType) {
            switch (reportType) {
            case Report1:
                Report1 report1 = new Report1( "1", "2");
                log.info( report1);
                break;
            case ReportN:
                ReportN reportN = new ReportN( "3", "4");
                log.info( reportN);
                break;
            }
        }
    }

    public static void main( String[] args) {
        try {
            log.info( "main:start");
            FacadeHelper facadeHelper = new FacadeHelper();
            facadeHelper.generateReport( ReportType.Report1);
            facadeHelper.generateReport( ReportType.ReportN);

        } catch( Exception e) {
            log.error( e.getMessage(), e);
        } finally {
            log.info( "main:finish");
        }
    }
}
