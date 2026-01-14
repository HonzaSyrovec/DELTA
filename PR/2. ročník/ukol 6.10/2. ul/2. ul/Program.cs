namespace _2._ul
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Random rnd = new Random();
            int[,] matice = new int[5, 5];

            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 5; j++)
                    matice[i, j] = rnd.Next(1, 51);

            for (int i = 0; i < 5; i++)
            {
                for (int j = 0; j < 5; j++)
                    Console.Write($"{matice[i, j],3} ");
                Console.WriteLine();
            }

            int nejmensi = matice[0, 0];
            int nejvetsi = matice[0, 0];
            int soucet = 0;

            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 5; j++)
                {
                    int hodnota = matice[i, j];
                    if (hodnota < nejmensi) nejmensi = hodnota;
                    if (hodnota > nejvetsi) nejvetsi = hodnota;
                    soucet += hodnota;
                }
            Console.WriteLine("");
            Console.WriteLine($"Nejmenší hodnota v matici: {nejmensi}");
            Console.WriteLine($"Největší hodnota v matici: {nejvetsi}");
            Console.WriteLine($"Součet všech hodnot v matici: {soucet}");
            Console.WriteLine("");
            Console.WriteLine("Hlavní diagonála:");
            for (int i = 0; i < 5; i++)
                Console.Write(matice[i, i] + " ");
            Console.WriteLine();
        }
    }
}
