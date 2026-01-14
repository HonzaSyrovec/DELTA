namespace _1._ul
{
    internal class Program
    {
        static void Main(string[] args)
        {
            int [] pole = new int[10];
            int nejvetsi_v_poli = 0;
            int nejmensi_v_poli = 101;
            for (int i = 0; i < pole.Length; i++)
            {
                Random random = new Random();
                int cislo = random.Next(1,100);
                pole[i] = cislo;
            }

            Console.WriteLine("pole vytvořeno");
            Console.Write("výpis: ");
            for (int i = 0;i < pole.Length; i++)
            {
                Console.Write(pole[i] + ", ");
            }
            Console.WriteLine("");
            for (int i = 0;i <pole.Length; i++)
            {
                if (pole[i] < nejmensi_v_poli) nejmensi_v_poli = pole[i];
                if (pole[i] > nejvetsi_v_poli) nejvetsi_v_poli = pole[i];
            }
            Console.WriteLine("nejvetší v poli: " + nejvetsi_v_poli);
            Console.WriteLine("nejmenší v poli: " + nejmensi_v_poli);

            double soucet = 0;
            for (int i = 0;i < pole.Length;i++)
            {
                soucet += pole[i];
            }double prumer = soucet / pole.Length;
            Console.WriteLine("průměr: " + prumer);
            int[] vzestupnepole = (int[])pole.Clone();
            int[] sestupnepole = (int[])pole.Clone();
            Array.Sort(sestupnepole);
            Array.Reverse(sestupnepole);
            Array.Sort(vzestupnepole);

            Console.Write("Vzestupně: ");
            for (int i = 0; i < pole.Length; i++)
            {
                Console.Write(vzestupnepole[i] + ", ");
            }
            Console.WriteLine("");
            Console.Write("Sestupně: ");
            for (int i = 0; i < pole.Length; i++)
            {
                Console.Write(sestupnepole[i] + ", ");
            }


        }
    }
}
