package uberlite.dao;

import uberlite.model.Location;
import uberlite.model.Ride;

public class EconomyRide implements RideCategory {
	private static final String BASE_NAME = "economy";
    private static final double BASE_FARE = 50.0;
    private static final double PER_KM_FARE = 10.0;

    @Override
    public Double calculateFare(Ride ride) {
        double distance = calculateDistance(ride.getStartLocation(), ride.getDestination());
        return BASE_FARE + (distance * PER_KM_FARE);
    }
    
    @Override
    public int getCapacity() {
        return 4;
    }
    
    private double calculateDistance(Location start, Location end) {
        // Calculate the distance between the two locations using a suitable algorithm
        // For example, using the Haversine formula:
        double R = 6371; // Radius of the earth in kilometers
        double lat1 = Math.toRadians(start.getLatitude());
        double lat2 = Math.toRadians(end.getLatitude());
        double dLat = Math.toRadians(end.getLatitude() - start.getLatitude());
        double dLng = Math.toRadians(end.getLongitude() - start.getLongitude());
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                 + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // Distance in kilometers
        return distance;
    }

    @Override
	public String getName() {
		// TODO Auto-generated method stub
		return BASE_NAME;
	}
}
