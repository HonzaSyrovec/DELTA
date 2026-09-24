using System;
using System.Collections.Generic;

namespace _5._ul
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Dictionary<string, int> studenti = new Dictionary<string, int>();

            studenti.Add("Tomáš", 1);
            studenti.Add("Jakub", 2);
            studenti.Add("Veronika", 3);
            studenti.Add("David", 1);
            studenti.Add("Eva", 2);

            Console.WriteLine("seznam studentů:");
            foreach (var par in studenti)
            {
                Console.WriteLine($"{par.Key}: {par.Value}");
            }

            Console.Write("zadej jméno studenta pro úpravu známky: ");
            string jmeno = Console.ReadLine();

            if (studenti.ContainsKey(jmeno))
            {
                Console.Write("zadej novou známku: ");
                int nova_znamka = int.Parse(Console.ReadLine());
                studenti[jmeno] = nova_znamka;
                Console.WriteLine("známka byla upravena.");
            }
            else
            {
                Console.WriteLine("student nebyl nalezen.");
            }

            Console.WriteLine("aktualizovaný seznam:");
            foreach (var par in studenti)
            {
                Console.WriteLine($"{par.Key}: {par.Value}");
            }
        }
    }
}
