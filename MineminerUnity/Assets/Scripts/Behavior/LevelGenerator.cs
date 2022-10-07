using System;
using System.Collections.Generic;
using Logic;
using UnityEngine;
using Random = UnityEngine.Random;

namespace Behavior
{
    [Serializable]
    public class OreModel
    {
        public Ore ore;
        public GameObject prefab;
    }

    public class LevelGenerator : MonoBehaviour
    {
        public static int FIELD_SIZE = 5;
        static int MIN_BLOCKS = 10;
        static float BLOCK_SIZE = 0.6f;
    
        public GameObject blocksHolder;
        public List<OreModel> origins;

        public List<OreCube> GenerateLevel()
        {
            var result = new List<OreCube>();
            var columns = new int[FIELD_SIZE, FIELD_SIZE];

            var count = 0;
            for (var i=0;i<FIELD_SIZE;i++)
            for (var j=0;j<FIELD_SIZE;j++)
            {
                columns[i, j] = (int)Math.Round(Random.value * FIELD_SIZE);
                count += columns[i, j];
            }

            while (count < MIN_BLOCKS)
            {
                var i = (int)Math.Round(Random.value * (FIELD_SIZE-1));
                var j = (int)Math.Round(Random.value * (FIELD_SIZE-1));
                if (columns[i, j] < FIELD_SIZE)
                {
                    columns[i, j]++;
                    count++;
                }
            }

            Sorter.Sort2D(columns);
        
            for (var i=0;i<FIELD_SIZE;i++)
            for (var j=0;j<FIELD_SIZE;j++)
            for (var u=0;u<columns[i,j];u++)
            {
                var ore = OreCube.GetOreByNumber((int)Math.Round(Random.value * 3));
                var coordinates = new Vector3(i*BLOCK_SIZE, u*BLOCK_SIZE, j*BLOCK_SIZE);
                GameObject prefab = Instantiate(FindOriginByOre(ore),coordinates, Quaternion.Euler(0,0,0), blocksHolder.transform);
                result.Add(new OreCube(ore, prefab));
            }

            return result;
        }

        private GameObject FindOriginByOre(Ore ore)
        {
            foreach (var model in origins)
                if (model.ore == ore)
                    return model.prefab;
            
            return null;
        }
    }
}