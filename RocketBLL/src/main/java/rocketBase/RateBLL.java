package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketData.LoanRequest;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	private final static double DINCOMEPCT1 = 0.28;
	private final static double DINCOMEPCT2 = 0.36;

	public static double getRate(int GivenCreditScore) throws RateException {

		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		double dInterestRate = 0;
		for (RateDomainModel r : rates) {
			if (GivenCreditScore > r.getiMinCreditScore()) {
				dInterestRate = r.getdInterestRate();
			}

			else {
				break;
			}

		}
		if (dInterestRate == 0) {
			throw new RateException();
		}
		return dInterestRate;
	}

	public static double getPayment(double r, double n, double p, double f, boolean t) {
		return FinanceLib.pmt(r, n, p, f, t) * (-1);
	}

	public static boolean checkIncomeExpenses(LoanRequest lq) {
		boolean bReturnValue = false;

		if (((lq.getIncome() / 12) * DINCOMEPCT1) > lq.getdAmount()) {
			if ((((lq.getIncome() / 12) + (lq.getExpenses())) * DINCOMEPCT2) > lq.getdAmount()) {
				bReturnValue = true;
			}
		}

		return bReturnValue;
	}

}