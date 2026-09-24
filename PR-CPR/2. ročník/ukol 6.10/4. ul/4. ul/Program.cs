using System;
using System.Collections.Generic;

namespace _4._ul
{
    internal class Program
    {
        static void Main(string[] args)
        {
            List<int> cisla = new List<int>();
            Random random = new Random();

            for (int i = 0; i < 20; i++)
            {
                cisla.Add(random.Next(1, 101));
            }

            Console.WriteLine("původní list:");
            foreach (int x in cisla)
            {
                Console.Write(x + " ");
            }
            Console.WriteLine();

            cisla.Add(random.Next(1, 101));

            for (int i = 0; i < cisla.Count; i++)
            {
                if (cisla[i] % 2 == 0)
                {
                    cisla.RemoveAt(i);
                    i--;
                }
            }

            Console.WriteLine("finální list:");
            foreach (int x in cisla)
            {
                Console.Write(x + " ");
            }
            Console.WriteLine();
        }
    }
}
