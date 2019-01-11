import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

class Main
{
    // This class determines how long it would take one planet to crash into another, such as if the Earth stopped orbiting the
    // Sun.  This doesn't happen now since the Earth is moving around, so it's forward velocity counteracts the
    // centripetal force from the Sun, giving it a circular orbit around the Sun.

    public static void main (String[] args)
    {
        while (true) // Loop contains the whole program:
        {
            Scanner scan = new Scanner (System.in);

            // Variables:

            double G = 6.67 * Math.pow(10, -11);

            // Tell user to enter mass of first planet:

            System.out.println ("Enter the mass of the first planet, in kg:");

            double mass_one = scan.nextDouble();

            // Tell user to enter mass of second planet:

            System.out.println ("Enter the mass of the second planet, in kg:");

            double mass_two = scan.nextDouble();

            // Tell user to enter distance between planets:

            System.out.println ("Enter the distance between the two planets, in meters:");

            double distance = scan.nextDouble();

            // Now I have everything I need.  Create the rest of the variables:

            double acceleration_one = 0; // Acceleration of planet one, at any given time (this changes, since distance changes).

            double acceleration_two = 0; // Acceleration of planet two, at any given time (this also changes).

            double velocity_one = 0; // Velocity of planet one, during the current time interval.

            double velocity_two = 0;  // Velocity of planet two, during the current time interval.

            double time = 0; // time it takes for planets to collide.  This will slowly increase.

            System.out.println ("Enter the time interval (in seconds) that you want the computer to recalculate values in.  Lower interval = slightly higher accuracy, but a longer wait.");

            double interval = scan.nextDouble();

            // I have all my variables.

            // Now to run a loop that, in each iteration, figures out how much distance each planet moves in 1 second.

            // At the beginning of the loop, it recalculates the new acceleration of the planets (since they are now
            // closer to each other, due to each moving for 1 second).

            // time += 1 at the end of each iteration.

            // distance -= how much EACH planet moves in each iteration.  When distance <= 0, loop breaks:

            double counter = 0;

            while (distance > 0)
            {
                // Let's work on Planet One first:

                // Its current acceleration is equal to:  G * mass_two / current distance squared.

                acceleration_one = (G * mass_two) / Math.pow(distance, 2);

                // Now to use this acceleration_one value to figure out planet one's distance it moves after the time interval:

                double distance_one = (velocity_one * interval) + 0.5 * acceleration_one * Math.pow(interval, 2);

                // Now to figure out the new velocity at the end of the interval, using the formula:

                // vf = vi + at

                velocity_one = velocity_one + acceleration_one * interval;

                // Check if light speed has been exceeded:

                if (velocity_one > 299792458)
                {
                    velocity_one = 299792458.0 - 0.0001;
                }

                ///////////////////////////////////////////////////////////////////////////////////////////////////////

                // Now to work on Planet Two:

                // Its current acceleration is equal to:  G * mass_one / current distance squared.

                acceleration_two = (G * mass_one) / Math.pow(distance, 2);

                // Now to use this acceleration_two value to figure out planet two's distance it moves after the time interval:

                double distance_two = (velocity_two * interval) + 0.5 * acceleration_two * Math.pow(interval, 2);

                // Now to figure out the new velocity at the end of the interval, using the formula:

                // vf = vi + at

                velocity_two = velocity_two + acceleration_two * interval;

                // Check if light speed has been exceeded:

                if (velocity_two > 299792458)
                {
                    velocity_two = 299792458.0 - 0.0001;
                }

                time += interval;

                distance -= (distance_one + distance_two); // subtracting the distances that both planets moved.

                // Loop iterates again, presuming distance > 0:

                counter++;

                if (counter % 1000000 == 0)
                {
                    System.out.println (counter + " iterations done.");
                }
            }

            // Now to output the time.  Which unit is used (seconds, days, years, etc) depends on how big / small
            // the values is.  Currently, the value is in seconds:

            if (time < 60) // Point where minutes start.
            {
                System.out.println ("The time is: " + time + " seconds");
            }

            else if (time < 60 * 60) // Point where hours start.
            {
                System.out.println ("The time is: " + time / 60 + " minutes");
            }

            else if (time < 60 * 60 * 24) // Point where days start.
            {
                System.out.println ("The time is: " + time / 60 / 60 + " hours");
            }

            else if (time < 60 * 60 * 24 * 365) // Point where years start.
            {
                System.out.println ("The time is: " + time / 60 / 60 / 24 + " days");
            }

            else if (time < 60 * 60 * 24 * 365 * Math.pow(10, 3)) // Point where thousand years start.
            {
                System.out.println ("The time is: " + time / 60 / 60 / 24 / 365 + " years");
            }

            else if (time < 60 * 60 * 24 * 365 * Math.pow(10, 6)) // Point where million years start.
            {
                System.out.println ("The time is: " + time / 60 / 60 / 24 / 365 / Math.pow(10, 3) + " thousand years");
            }

            else if (time < 60 * 60 * 24 * 365 * Math.pow(10, 9)) // Point where billion years start.
            {
                System.out.println ("The time is: " + time / 60 / 60 / 24 / 365 / Math.pow(10, 6) + " million years");
            }

            else if (time < 60 * 60 * 24 * 365 * Math.pow(10, 12)) // Point where trillion years start.
            {
                System.out.println ("The time is: " + time / 60 / 60 / 24 / 365 / Math.pow(10, 9) + " billion years");
            }

            else // Now time is > trillion, so it will just be expressed as x trillion years.
            {
                System.out.println ("The time is: " + time / 60 / 60 / 24 / 365 / Math.pow(10, 12) + " trillion years");
            }

            Scanner read = new Scanner (System.in);

            System.out.println ("\nTo run the program again, press enter.");

            String again = read.nextLine();

            if (!(again.equals("")))
            {
                break;
            }
        }
    }
}