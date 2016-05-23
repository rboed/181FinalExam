package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {

	@Test
	public void getInterestRateTest() {

		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();

		for (RateDomainModel r : rates) {
			System.out.print("Credit Score: " + r.getiMinCreditScore());
			System.out.print("");
			System.out.printf("Interest Rate: %f", r.getdInterestRate());
			System.out.println("");

			if (r.getiMinCreditScore() == 600) {
				assertTrue(r.getdInterestRate() == 5.0);
			}
			if (r.getiMinCreditScore() == 650) {
				assertTrue(r.getdInterestRate() == 4.5);
			}
			if (r.getiMinCreditScore() == 700) {
				assertTrue(r.getdInterestRate() == 4.0);
			}
			if (r.getiMinCreditScore() == 750) {
				assertTrue(r.getdInterestRate() == 3.75);
			}
			if (r.getiMinCreditScore() == 800) {
				assertTrue(r.getdInterestRate() == 3.5);
			}
		}
		assert (rates.size() > 0);
	}
}