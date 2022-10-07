using UnityEngine;

namespace Logic
{
    public enum Ore
    {
        IRON,
        DIAMOND,
        GOLD,
        DIRT
    }

    public class OreCube
    {
        public Ore ore;
        public GameObject prefab;

        public OreCube(Ore ore, GameObject prefab)
        {
            this.ore = ore;
            this.prefab = prefab;
        }
        
        public static Ore GetOreByNumber(int num)
        {
            switch (num)
            {
                case 0:
                    return Ore.IRON;
                case 1:
                    return Ore.DIAMOND;
                case 2:
                    return Ore.GOLD;
                case 3:
                    return Ore.DIRT;
            }

            return Ore.DIRT;
        }
    }
}