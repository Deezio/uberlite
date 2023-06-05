package uberlite.dao;

import uberlite.model.Ride;

public interface RideCategory {
	public static final String name = "";
   public Double calculateFare(Ride ride);
   int getCapacity();
   String getName();
}
