using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _7._ul
{
    class auto
    {
        public string znacka;
        public string model;
        public int rok_vyroby;
        public int najete_kilometry;
    }

    class autopark
    {
        public List<auto> auta = new List<auto>();

        public void pridej_auto(auto nove_auto)
        {
            auta.Add(nove_auto);
            Console.WriteLine("auto bylo přidáno.");
        }

        public void odstran_auto(string znacka)
        {
            auto nalezene = null;
            foreach (auto x in auta)
            {
                if (x.znacka == znacka)
                {
                    nalezene = x;
                    break;
                }
            }
            if (nalezene != null)
            {
                auta.Remove(nalezene);
                Console.WriteLine("auto bylo odstraněno.");
            }
            else
            {
                Console.WriteLine("auto s danou značkou nebylo nalezeno.");
            }
        }

        public void vypis_auta()
        {
            if (auta.Count == 0)
            {
                Console.WriteLine("v autoparku nejsou žádná auta.");
                return;
            }

            Console.WriteLine("auta v autoparku:");
            foreach (auto x in auta)
            {
                Console.WriteLine($"{x.znacka} {x.model}, rok {x.rok_vyroby}, {x.najete_kilometry} km");
            }
        }
    }
}

