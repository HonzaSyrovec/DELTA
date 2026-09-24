namespace _8._ul
{
    internal class Program
    {
        static void Main(string[] args)
        {
            int[,] matice = new int[4, 4];
            Random random = new Random();

            for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    matice[i, j] = random.Next(1, 101);
                }
            }

            int nejvetsi_soucet = int.MinValue;
            int index_radku = 0;

            for (int i = 0; i < 4; i++)
            {
                int soucet = 0;
                for (int j = 0; j < 4; j++)
                {
                    soucet += matice[i, j];
                    Console.Write(matice[i, j] + " ");
                }
                Console.WriteLine($"| součet řádku: {soucet}");
                if (soucet > nejvetsi_soucet)
                {
                    nejvetsi_soucet = soucet;
                    index_radku = i;
                }
            }

            Console.WriteLine($"řádek s největším součtem je {index_radku + 1} s hodnotou {nejvetsi_soucet}");
        }
    }
}
