using System;

namespace _3._ul
{
    internal class Program
    {
        static void Main(string[] args)
        {
            int[] pole = null;
            bool konec = false;

            while (!konec)
            {
                Console.WriteLine("\nvyber akci:");
                Console.WriteLine("1, vytvoření pole");
                Console.WriteLine("2, výpis pole");
                Console.WriteLine("3, najdi nejmenší hodnotu");
                Console.WriteLine("4, najdi největší hodnotu");
                Console.WriteLine("5, vypočítej průměr pole");
                Console.WriteLine("6, konec programu");
                Console.Write("zadej volbu: ");

                try
                {
                    int volba = int.Parse(Console.ReadLine());

                    switch (volba)
                    {
                        case 1:
                            Console.Write("zadejte velikost pole: ");
                            int velikost = int.Parse(Console.ReadLine());
                            pole = vytvor_pole(velikost);
                            Console.WriteLine("pole bylo vytvořeno.");
                            break;

                        case 2:
                            if (pole != null)
                                vypis_pole(pole);
                            else
                                Console.WriteLine("pole zatím nebylo vytvořeno!");
                            break;

                        case 3:
                            if (pole != null)
                                najdi_minimum(pole);
                            else
                                Console.WriteLine("pole zatím nebylo vytvořeno!");
                            break;

                        case 4:
                            if (pole != null)
                                najdi_maximum(pole);
                            else
                                Console.WriteLine("pole zatím nebylo vytvořeno!");
                            break;

                        case 5:
                            if (pole != null)
                                vypocitej_prumer(pole);
                            else
                                Console.WriteLine("pole zatím nebylo vytvořeno!");
                            break;

                        case 6:
                            konec = true;
                            Console.WriteLine("konec programu.");
                            break;

                        default:
                            Console.WriteLine("neplatná volba!");
                            break;
                    }
                }
                catch (Exception ex)
                {
                    Console.WriteLine("chyba: " + ex.Message);
                }
            }
        }

        public static int[] vytvor_pole(int velikost)
        {
            int[] pole = new int[velikost];
            Random nahoda = new Random();
            for (int i = 0; i < pole.Length; i++)
            {
                pole[i] = nahoda.Next(1, 101);
            }
            return pole;
        }

        public static void vypis_pole(int[] pole)
        {
            Console.Write("pole: ");
            for (int i = 0; i < pole.Length; i++)
            {
                Console.Write(pole[i] + " ");
            }
            Console.WriteLine();
        }

        public static void najdi_minimum(int[] pole)
        {
            int nejmensi = int.MaxValue;
            for (int i = 0; i < pole.Length; i++)
            {
                if (pole[i] < nejmensi)
                    nejmensi = pole[i];
            }
            Console.WriteLine($"nejmenší hodnota: {nejmensi}");
        }

        public static void najdi_maximum(int[] pole)
        {
            int nejvetsi = int.MinValue;
            for (int i = 0; i < pole.Length; i++)
            {
                if (pole[i] > nejvetsi)
                    nejvetsi = pole[i];
            }
            Console.WriteLine($"největší hodnota: {nejvetsi}");
        }

        public static void vypocitej_prumer(int[] pole)
        {
            int soucet = 0;
            for (int i = 0; i < pole.Length; i++)
            {
                soucet += pole[i];
            }
            double prumer = (double)soucet / pole.Length;
            Console.WriteLine($"průměr: {prumer:F2}");
        }
    }
}