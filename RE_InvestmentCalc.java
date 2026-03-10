public class RE_InvestmentCalc {
    public static void main(String[] args) {
        System.out.println();
        System.out.println("Data format: list, seller covered percentage, old list, yearly taxes, tax rate, monthly fees, mortgage rate in basis, downpayment percent in basis, repair costs");
        double list = Double.parseDouble(args[0]);
        double sellercover = Double.parseDouble(args[1]);
        double downpayp = Double.parseDouble(args[7]) / 100;
        if(sellercover > 3 || sellercover < 0){
            sellercover = 0;
            System.out.println("sellercover > 3 || sellercover < 0");
        }
        double feeperc = (1 + ((3 - sellercover) / 100));
        double price1 = list * feeperc;
        double downpay1 = price1 * downpayp;
        double oldlist = Double.parseDouble(args[2]);
        double drop = oldlist - list;
        double discount = 10000 + (drop / 5);
        double price2 = (list - discount) * feeperc;
        double downpay2 = price2 * downpayp;
        double taxes = Double.parseDouble(args[3]);
        double taxrt = Double.parseDouble(args[4]);
        double possibletax = (taxrt / 100) * list;
        double monthlyfees = Double.parseDouble(args[5]);
        if(monthlyfees < 0){
            monthlyfees = 0;
            System.out.println("monthlyfees < 0");
        }
        double mortgagert = Double.parseDouble(args[6]) / 100;
        if(mortgagert < 0){
            mortgagert = 0;
            System.out.println("mortgagert < 0");
        }
        double repairs = Double.parseDouble(args[8]);
        double rdownpay1 = downpay1 + repairs;
        double rdownpay2 = downpay2 + repairs;
        double maintain = (oldlist + repairs) / 1200;
        double fixedlow1 = monthlyfees + (taxes / 12) + maintain + (mortgagert * (price1 - downpay1) / 12);
        double fixedhigh1 = monthlyfees + (possibletax / 12) + maintain + (mortgagert * (price1 - downpay1) / 12);
        double fixedlow2 = monthlyfees + (taxes / 12) + maintain + (mortgagert * (price2 - downpay2) / 12);
        double fixedhigh2 = monthlyfees + (possibletax / 12) + maintain + (mortgagert * (price2 - downpay2) / 12);
        double returnobj1 = 0.1 / 12;
        double returnobj2 = 0.15 / 12;
        double returnobj3 = 0.2 / 12;
        double rent11low = (returnobj1 * rdownpay1) + fixedlow1;
        double rent21low = (returnobj2 * rdownpay1) + fixedlow1;
        double rent31low = (returnobj3 * rdownpay1) + fixedlow1;
        double rent11high = (returnobj1 * rdownpay1) + fixedhigh1;
        double rent21high = (returnobj2 * rdownpay1) + fixedhigh1;
        double rent31high = (returnobj3 * rdownpay1) + fixedhigh1;
        double rent12low = (returnobj1 * rdownpay2) + fixedlow2;
        double rent22low = (returnobj2 * rdownpay2) + fixedlow2;
        double rent32low = (returnobj3 * rdownpay2) + fixedlow2;
        double rent12high = (returnobj1 * rdownpay2) + fixedhigh2;
        double rent22high = (returnobj2 * rdownpay2) + fixedhigh2;
        double rent32high = (returnobj3 * rdownpay2) + fixedhigh2;
        double expec1 = (0.638853709 * list) - (25000 * list / 300000);
        double expec2 = (0.638853709 * (list - discount)) - (25000 * (list - discount) / 300000);
        System.out.println();
        System.out.println("List Price: $" + ("" + list + "00").substring(0 , ("" + list).indexOf(".") + 3));
        System.out.println("Real Price: $" + ("" + price1 + "00").substring(0 , ("" + price1).indexOf(".") + 3));
        System.out.println("Hope Price: $" + ("" + price2 + "00").substring(0 , ("" + price2).indexOf(".") + 3));
        System.out.println();
        System.out.println("Assumed Monthly Maintaince: $" + ("" + maintain + "00").substring(0 , ("" + maintain).indexOf(".") + 3));
        System.out.println("Possible Yearly Tax Range: $" + ("" + taxes + "00").substring(0 , ("" + taxes).indexOf(".") + 3) + " - $" + ("" + possibletax + "00").substring(0 , ("" + possibletax).indexOf(".") + 3));
        System.out.println();
        System.out.println("For Price: $" + price1);
        System.out.println("Bank's Cut Every Month: $" + ("" + (mortgagert * (price1 - downpay1) / 12)).substring(0 , ("" + (mortgagert * (price1 - downpay1) / 12)).indexOf(".") + 3));
        System.out.println("Vacancy Cost w/o Taxes: $" + ("" + ((mortgagert * (price1 - downpay1) / 12) + maintain + monthlyfees)).substring(0 , ("" + ((mortgagert * (price1 - downpay1) / 12) + maintain + monthlyfees)).indexOf(".") + 3) + " a month");
        System.out.println("Total out of Pocket: $" + ("" + rdownpay1 + "00").substring(0 , ("" + rdownpay1).indexOf(".") + 3));
        System.out.println("Expected Appreciation: $" + ("" + expec1 + "00").substring(0 , ("" + expec1).indexOf(".") + 3) + " ROI: " + ("" + (100 * expec1 / rdownpay1) + "00").substring(0 , ("" + (100 * expec1 / rdownpay1)).indexOf(".") + 3) + "%");
        System.out.println("For Low Taxes: 10% ROI: $" + ("" + rent11low + "00").substring(0 , ("" + rent11low).indexOf(".") + 3) + " a month. 15% ROI: $" + ("" + rent21low + "00").substring(0 , ("" + rent21low).indexOf(".") + 3) + " a month. 20% ROI: $" + ("" + rent31low + "00").substring(0 , ("" + rent31low).indexOf(".") + 3) + " a month.");
        System.out.println("For High Taxes: 10% ROI: $" + ("" + rent11high + "00").substring(0 , ("" + rent11high).indexOf(".") + 3) + " a month. 15% ROI: $" + ("" + rent21high + "00").substring(0 , ("" + rent21high).indexOf(".") + 3) + " a month. 20% ROI: $" + ("" + rent31high + "00").substring(0 , ("" + rent31high).indexOf(".") + 3) + " a month.");
        System.out.println();
        System.out.println("For Price: $" + price2);
        System.out.println("Bank's Cut Every Month: $" + ("" + (mortgagert * (price2 - downpay2) / 12)).substring(0 , ("" + (mortgagert * (price2 - downpay2) / 12)).indexOf(".") + 3));
        System.out.println("Vacancy Cost w/o Taxes: $" + ("" + ((mortgagert * (price2 - downpay2) / 12) + maintain + monthlyfees)).substring(0 , ("" + ((mortgagert * (price2 - downpay2) / 12) + maintain + monthlyfees)).indexOf(".") + 3) + " a month");
        System.out.println("Total out of Pocket: $" + ("" + rdownpay2 + "00").substring(0 , ("" + rdownpay2).indexOf(".") + 3));
        System.out.println("Expected Appreciation: $" + ("" + expec2 + "00").substring(0 , ("" + expec2).indexOf(".") + 3) + " ROI: " + ("" + (100 * expec2 / rdownpay2) + "00").substring(0 , ("" + (100 * expec2 / rdownpay2)).indexOf(".") + 3) + "%");
        System.out.println("For Low Taxes: 10% ROI: $" + ("" + rent12low + "00").substring(0 , ("" + rent12low).indexOf(".") + 3) + " a month. 15% ROI: $" + ("" + rent22low + "00").substring(0 , ("" + rent22low).indexOf(".") + 3) + " a month. 20% ROI: $" + ("" + rent32low + "00").substring(0 , ("" + rent32low).indexOf(".") + 3) + " a month.");
        System.out.println("For High Taxes: 10% ROI: $" + ("" + rent12high + "00").substring(0 , ("" + rent12high).indexOf(".") + 3) + " a month. 15% ROI: $" + ("" + rent22high + "00").substring(0 , ("" + rent22high).indexOf(".") + 3) + " a month. 20% ROI: $" + ("" + rent32high + "00").substring(0 , ("" + rent32high).indexOf(".") + 3) + " a month.");
        System.out.println();
        boolean stop11 = true;
        boolean stop21 = true;
        boolean stop12 = true;
        boolean stop22 = true;
        if ((100 * ((expec1 / rdownpay1) + 2)) < 400) {
            System.out.println("For Price: $" + price1);
            System.out.println("Total Return Unacceptable at 20% ROI Rent");
            System.out.println((40 - (10 * ((expec1 / rdownpay1) + 2)) + 20) + "% ROI Needed");
            System.out.println("Needed Rent $" + ((((40 - (10 * ((expec1 / rdownpay1) + 2)) + 20) / 1200) * rdownpay1) + fixedlow1));
            System.out.println();
            stop11 = false;
            stop21 = false;
        }
        if (stop11 && ((100 * ((expec1 / rdownpay1) + 1.5)) < 400)) {
            System.out.println("For Price: $" + price1);
            System.out.println("Total Return Unacceptable at 15% ROI Rent");
            System.out.println((40 - (10 * ((expec1 / rdownpay1) + 1.5)) + 15) + "% ROI Needed");
            System.out.println("Needed Rent $" + ((((40 - (10 * ((expec1 / rdownpay1) + 2)) + 15) / 1200) * rdownpay1) + fixedlow1));
            System.out.println();
            stop21 = false;
        }
        if (stop21 && ((100 * ((expec1 / rdownpay1) + 1)) < 400)) {
            System.out.println("For Price: $" + price1);
            System.out.println("Total Return Unacceptable at 10% ROI Rent");
            System.out.println((40 - (10 * ((expec1 / rdownpay1) + 1)) + 10) + "% ROI Needed");
            System.out.println("Needed Rent $" + ((((40 - (10 * ((expec1 / rdownpay1) + 2)) + 10) / 1200) * rdownpay1) + fixedlow1));
            System.out.println();
        }
        if ((100 * ((expec2 / rdownpay2) + 2)) < 400) {
            System.out.println("For Price: $" + price2);
            System.out.println("Total Return Unacceptable at 20% ROI Rent");
            System.out.println((40 - (10 * ((expec2 / rdownpay2) + 2)) + 20) + "% ROI Needed");
            System.out.println("Needed Rent $" + ((((40 - (10 * ((expec2 / rdownpay2) + 2)) + 20) / 1200) * rdownpay2) + fixedlow2));
            System.out.println();
            stop12 = false;
            stop22 = false;
        }
        if (stop12 && ((100 * ((expec2 / rdownpay2) + 1.5)) < 400)) {
            System.out.println("For Price: $" + price2);
            System.out.println("Total Return Unacceptable at 15% ROI Rent");
            System.out.println((40 - (10 * ((expec2 / rdownpay2) + 1.5)) + 15) + "% ROI Needed");
            System.out.println("Needed Rent $" + ((((40 - (10 * ((expec2 / rdownpay2) + 2)) + 15) / 1200) * rdownpay2) + fixedlow2));
            System.out.println();
            stop22 = false;
        }
        if (stop22 && ((100 * ((expec2 / rdownpay2) + 1)) < 400)) {
            System.out.println("For Price: $" + price2);
            System.out.println("Total Return Unacceptable at 10% ROI Rent");
            System.out.println((40 - (10 * ((expec2 / rdownpay2) + 1)) + 10) + "% ROI Needed");
            System.out.println("Needed Rent $" + ((((40 - (10 * ((expec2 / rdownpay2) + 2)) + 10) / 1200) * rdownpay2) + fixedlow2));
            System.out.println();
        }
    }
}