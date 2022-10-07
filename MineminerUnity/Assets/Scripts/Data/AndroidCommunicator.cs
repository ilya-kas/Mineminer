using Logic;
using UnityEngine;

namespace Data
{
    public class AndroidCommunicator
    {
        private AndroidJavaObject communicator;

        public AndroidCommunicator()
        {
            //AndroidJavaClass jc = new AndroidJavaClass("com.rubon.mineminer.AndroidUnityCommunicator");
            //communicator = jc.GetStatic<AndroidJavaObject>("instance");
        }

        public bool CanFarmBlock(Ore ore)
        {
            AndroidJavaClass jc = new AndroidJavaClass("com.rubon.mineminer.AndroidUnityCommunicator");
            communicator = jc.GetStatic<AndroidJavaObject>("instance");
            bool response = communicator.Call<bool>("canFarmBlock", ore.ToString());
            return response;
        }
    }
}