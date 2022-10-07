using Behavior;

namespace Logic
{
    public class Sorter
    {
        public static void Sort2D(int[,] a)
        {
            var changed = true;
            while (changed)
            {
                changed = Sort2DLeft(a);
                changed = changed || Sort2DRight(a);
            }
        }

        static bool Sort2DLeft(int[,] a)
        {
            var changed = false;
            for (int t = 0; t < LevelGenerator.FIELD_SIZE; t++)
            for (int i=0;i<LevelGenerator.FIELD_SIZE;i++)
            for (int j=i;j<LevelGenerator.FIELD_SIZE;j++)
                if (a[i, t] < a[j, t])
                {
                    (a[i, t], a[j, t]) = (a[j, t], a[i, t]);
                    changed = true;
                }

            return changed;
        }

        static bool Sort2DRight(int[,] a)
        {
            var changed = false;
            for (int t = 0; t < LevelGenerator.FIELD_SIZE; t++)
            for (int i=0; i<LevelGenerator.FIELD_SIZE; i++)
            for (int j=i; j<LevelGenerator.FIELD_SIZE; j++)
                if (a[t, i] < a[t, j])
                {
                    (a[t, i], a[t, j]) = (a[t, j], a[t, i]);
                    changed = true;
                }

            return changed;
        }
    }
}