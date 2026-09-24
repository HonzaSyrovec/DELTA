using System;
using System.Collections.Generic;

namespace _7._ul
{
    internal class Program
    {
        static void Main(string[] args)
        {
            autopark park = new autopark();

            while (true)
            {
                Console.WriteLine("\n1, přidat auto");
                Console.WriteLine("2, odstranit auto");
                Console.WriteLine("3, vypsat všechna auta");
                Console.WriteLine("4, konec");
                Console.Write("zadej volbu: ");
                int volba = int.Parse(Console.ReadLine());

                if (volba == 1)
                {
                    auto nove_auto = new auto();
                    Console.Write("značka: ");
                    nove_auto.znacka = Console.ReadLine();
                    Console.Write("model: ");
                    nove_auto.model = Console.ReadLine();
                    Console.Write("rok výroby: ");
                    nove_auto.rok_vyroby = int.Parse(Console.ReadLine());
                    Console.Write("najaté kilometry: ");
                    nove_auto.najete_kilometry = int.Parse(Console.ReadLine());
                    park.pridej_auto(nove_auto);
                }
                else if (volba == 2)
                {
                    Console.Write("zadej značku auta pro odstranění: ");
                    string znacka = Console.ReadLine();
                    park.odstran_auto(znacka);
                }
                else if (volba == 3)
                {
                    park.vypis_auta();
                }
                else if (volba == 4)
                {
                    break;
                }
            }
        }
    }
}