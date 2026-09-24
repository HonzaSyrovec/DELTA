namespace _9._ul
{
    internal class program
    {
        static void Main(string[] args)
        {
            Console.Write("zadej slovo: ");
            string slovo = Console.ReadLine();
            slovo = slovo.ToLower();

            Dictionary<char, int> pocitadlo = new Dictionary<char, int>();

            foreach (char znak in slovo)
            {
                if (pocitadlo.ContainsKey(znak))
                    pocitadlo[znak]++;
                else
                    pocitadlo.Add(znak, 1);
            }

            Console.WriteLine("výskyty písmen:");
            foreach (var par in pocitadlo)
            {
                Console.WriteLine($"{par.Key}: {par.Value}");
            }
        }
    }
}
