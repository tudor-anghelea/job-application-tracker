package com.example.jobapplicationtracker.status;

public class Service {

    public static boolean isAllowed(Status old, Status updated)
    {
        switch (old)
        {
            case WISHLIST ->
            {
                return switch (updated) {
                    case APPLIED -> true;
                    default -> false;
                };
            }
            case APPLIED ->
            {
                return switch (updated) {
                    case ONLINE_ASSESSMENT, REJECTED, WITHDRAWN -> true;
                    default -> false;
                };
            }
            case ONLINE_ASSESSMENT ->
            {
                return switch (updated) {
                    case INTERVIEW, REJECTED, WITHDRAWN -> true;
                    default -> false;
                };
            }
            case INTERVIEW ->
            {
                return switch (updated) {
                    case OFFER, REJECTED, WITHDRAWN -> true;
                    default -> false;
                };
            }
            case OFFER ->
            {
                return switch (updated) {
                    case ACCEPTED, REJECTED -> true;
                    default -> false;
                };
            }
            default -> { return false; }
        }
    }
}
