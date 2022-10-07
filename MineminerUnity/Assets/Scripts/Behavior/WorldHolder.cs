using System;
using System.Collections.Generic;
using Data;
using Logic;
using UnityEngine;

namespace Behavior
{
    
    public class WorldHolder : MonoBehaviour
    {
        private List<OreCube> cubes;
        private LevelGenerator levelGenerator;
        private AndroidCommunicator communicator = new AndroidCommunicator();
        
        // Start is called before the first frame update
        void Start()
        {
            levelGenerator = GameObject.Find("GameManager").GetComponent<LevelGenerator>();
            cubes = levelGenerator.GenerateLevel();
        }

        public void RemoveCube(GameObject cubeToDel)
        {
            foreach (var cube in cubes)
                if (cube.prefab == cubeToDel)
                {
                    if (!communicator.CanFarmBlock(cube.ore)) return;
                    
                    cubes.Remove(cube);
                    Destroy(cube.prefab);

                    if (cubes.Count == 0)
                        cubes = levelGenerator.GenerateLevel();
                    return;
                }
        }
    }
}
