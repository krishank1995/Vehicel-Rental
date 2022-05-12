# Vehicle-Rental
Basic Vehicle Rental System

 
        Problem Summary :
        Rental Service ->
        1. Has 1:M branches across city
        2. Has limited number of different mix of vehicles
        3. Vehicle can be booked in slots (1hr slots in a day) at a fixed price

        Req:
        1. Add a new branch (Done)
        2. Add a vehicle of pre-existing type (Done)
        3. Rent/Book a vehicle (User Params: Vehicle Type, Time Slot ; System Params: Lowest Price, Extension:{Strategy:Higher Price for surge in demand slot?, nX factor) (Done)
        4. Show available vehicles sorted on price at a branch. (Done)
        5. Drop Vehicle from same branch (Done)
        6. Surge pricing as per some business rule as mentioned in point 3 (Done)
        

        
        Input:Output

        Input -> From a file mentioned in args
        Format:
        1. ADD_BRANCH BRANCH_NAME CSV_VEHICLE_TYPES
        2. ADD_VEHICLE BRANCH_NAME VEHICLE_TYPE ID PRICE
        3. BOOK BRANCH_NAME VEHICLE_TYPE START_TIME END_TIME [AS NOT SPECIFIED AND AS PER TEST CASES : ASSUME 24HR Format]
        4. DISPLAY_VEHICLES BRANCH_NAME START_TIME END_TIME

        Output -> Console
        1. ADD_BRANCH -> TRUE/FALSE
        2. ADD_VEHICLE -> TRUE/FALSE
        3. BOOK -> Booking Price on success else -1
        4. DISPLAY_VEHICLES -> CSV VEHICLE_NAMES
        5. DROP -> TRUE/FALSE
