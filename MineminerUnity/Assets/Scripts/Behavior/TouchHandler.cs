using UnityEngine;

namespace Behavior
{
    public class TouchHandler : MonoBehaviour
    {
        private WorldHolder worldHolder;
        public GameObject blocksHolder;
    
        // Start is called before the first frame update
        void Start()
        {
            worldHolder = GameObject.Find("GameManager").GetComponent<WorldHolder>();
        }

        void Update()
        {
            foreach (var touch in Input.touches) {
                if (touch.phase == TouchPhase.Began) {
                    var ray = Camera.main.ScreenPointToRay (touch.position);
                    RaycastHit hit;
                    if (Physics.Raycast (ray, out hit)) 
                        ProcessObjectTouch(hit.transform.gameObject);
                }
            }
        }

        void ProcessObjectTouch(GameObject touched)
        {
            if (touched.transform.parent == blocksHolder.transform)
            {
                worldHolder.RemoveCube(touched);
            }
        }
    }
}
