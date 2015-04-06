package com.my.algorithms;

import com.my.algorithms.tools.RandomUtils;
import com.my.algorithms.tools.Randoms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MysteryWildShuffle {

    private static final double[] SCENARIO_W_MG_1 = {0.01, 0.24, 0.05, 0.7};
    private static final double[] SCENARIO_W_MG_2 = {0.7, 0.24, 0.05, 0.01};
    private static final double[] SCENARIO_W_MG_3 = {0, 0.9, 0.09, 0.01};

    private static final int[] MW_POSITIONS = {
            0, 1, 2, 3, 4,
            5, 6, 7, 8, 9,
            10, 11, 12, 13, 14};

    private static final int[][] MW_POSITIONS_ON_REELS = {
            {0, 5, 10},
            {1, 6, 11},
            {2, 7, 12},
            {3, 8, 13},
            {4, 9, 14}
    };
    private static final int NUM_REELS = 5;

    private static double[][] scenario = new double[][]{SCENARIO_W_MG_3, SCENARIO_W_MG_2, SCENARIO_W_MG_1, SCENARIO_W_MG_2, SCENARIO_W_MG_1};

    private static final int[][] ADJACENT_POSITIONS = {
            {1, 5, 6},
            {0, 2, 3, 6, 7},
            {1, 3, 6, 7, 8},
            {2, 4, 7, 8, 9},
            {3, 8, 9},
            {0, 1, 6, 10, 11},
            {0, 1, 2, 5, 7, 10, 11, 12},
            {1, 2, 3, 6, 8, 11, 12, 13},
            {2, 3, 4, 7, 9, 12, 13, 14},
            {3, 4, 8, 13, 14},
            {5, 6, 11},
            {5, 6, 7, 10, 12},
            {6, 7, 8, 11, 13},
            {7, 8, 9, 12, 14},
            {8, 9, 13},
    };

    public static void main(String[] args) {

        final int[] mysteryWildsByReels = new int[scenario.length];
        int[] positions = new int[0];
        for (int i = 0; i < scenario.length; i++) {
            final int numberOfWilds = RandomUtils.randomIndexX(scenario[i]);
            mysteryWildsByReels[i] = numberOfWilds;
            final int[] tmp = Arrays.copyOfRange(RandomUtils.shuffle(MW_POSITIONS_ON_REELS[i]), 0, mysteryWildsByReels[i]);
            positions = concat(positions, tmp);
        }
        System.out.println("MW on reels: " + Arrays.toString(mysteryWildsByReels));
        System.out.println("MW positions: " + Arrays.toString(positions));

        System.out.println("\n" + toString(positions));

        for (final int screenIndex : positions) {
            final int x = screenIndex % NUM_REELS;
            final int y = screenIndex / NUM_REELS;
            System.out.println(x + " : " + y);
            // reels[x][(reels[x].length + result[x] + y - 1) % reels[x].length] = WILD;
        }

        final Set<Integer> pickedWilds = new HashSet<Integer>(MW_POSITIONS.length);

        // Mystery wilds in array
        int[] mysteryWilds = new int[0];
        for (int reelIndex = 0; reelIndex < mysteryWildsByReels.length; reelIndex++) {
            if (mysteryWildsByReels[reelIndex] > 0) {
                final int[] wilds = new int[mysteryWildsByReels[reelIndex]];
                Arrays.fill(wilds, reelIndex);
                mysteryWilds = concat(mysteryWilds, wilds);
            }
        }
        System.out.println("MW: " + Arrays.toString(mysteryWilds));

        // Pick uniformly one random wild
        final int mysteryWildIndex = Randoms.randInt(0, mysteryWilds.length);
        final int mysteryWildReel = mysteryWilds[mysteryWildIndex];
        System.out.println("MW index: " + mysteryWildIndex);
        System.out.println("MW reel index: " + mysteryWildReel);

        // a) remove mysteryWildIndex from mysteryWilds
        // b) decrease number of mysteryWildsByReels
        mysteryWildsByReels[mysteryWildReel] = mysteryWildsByReels[mysteryWildReel] - 1;

        // Pick uniformly position on the reel
        final int positionOnReel = Randoms.randInt(0, 2);
        final int position = MW_POSITIONS_ON_REELS[mysteryWildReel][positionOnReel];
        pickedWilds.add(position);
        System.out.println("MW picked position on reel: " + positionOnReel);
        System.out.println("MW picked position: " + position);
        System.out.println("Picked MWs: " + pickedWilds);

        // Put, if possible, adjacent wilds
        int[] adjacent = ADJACENT_POSITIONS[position];
        System.out.println("Adjacent MW: " + Arrays.toString(adjacent));

        final Set<Integer> pickedMysteryWilds = pickAdjacent(position, pickedWilds);

        // Return to the beginning: pick random mystery wild ...
    }

    private static int[] concat(int[] a, int[] b) {
        int aLen = a.length;
        int bLen = b.length;
        int[] c = new int[aLen + bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }

    private static Set<Integer> pickAdjacent(final int position, final Set<Integer> picked) {

        final Set<Integer> result = new HashSet<Integer>();

        final Set<Integer> adjacent = new HashSet(Arrays.asList(ADJACENT_POSITIONS[position]));
        final int x = position % NUM_REELS;

        // x - 1
        final Set<Integer> pickFrom = new HashSet<Integer>(adjacent);
        pickFrom.retainAll(Arrays.asList(MW_POSITIONS_ON_REELS[x - 1]));
        pickFrom.removeAll(picked);


        // x

        // x + 1

        return result;
    }

    private static String toString(final int[] wilds) {
        return toString(wilds, null);
    }

    private static String toString(final int[] wilds, final Integer pivot) {
        Arrays.sort(wilds);
        int index = 0;
        int wildIndex = 0;
        final StringBuilder result = new StringBuilder(70);
        while (index < MW_POSITIONS.length) {
            result.append(' ');
            if (pivot != null && pivot == index) {
                result.append(" +");
            } else if (wildIndex < wilds.length && index == wilds[wildIndex]) {
                result.append(" -");
                wildIndex++;
            } else {
                result.append(String.format("%2d", index));
            }
            result.append(' ');
            if ((index + 1) % 5 == 0) {
                result.append("\n");
            }
            index++;
        }
        return result.toString();
    }
}
