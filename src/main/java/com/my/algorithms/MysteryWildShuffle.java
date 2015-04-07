package com.my.algorithms;

import com.my.algorithms.tools.RandomUtils;
import com.my.algorithms.tools.Randoms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.my.algorithms.tools.Arrays.toObject;

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

        // generateMysteryWildsInMG();
        // generateMysteryWildsInFG();
        // generateMysteryWildsInFG(new int[]{1, 0, 3, 1, 3});

        generateMysteryWildsInFG(new int[]{3, 3, 3, 3, 3});
    }

    private static void generateMysteryWildsInMG() {

        final int[] mysteryWildsByReels = pickMysteryWildsByReels();
        final int[] mysteryWildsPositions = pickMysteryWildsPositions(mysteryWildsByReels);

        System.out.println("=== Mystery Wilds in Main Game ===\n");
        System.out.println("MW on reels: " + Arrays.toString(mysteryWildsByReels));
        System.out.println("MW mysteryWildsPositions: " + Arrays.toString(mysteryWildsPositions));
        System.out.println("\n" + toString(mysteryWildsPositions));

        for (final int screenIndex : mysteryWildsPositions) {
            final int x = screenIndex % NUM_REELS;
            final int y = screenIndex / NUM_REELS;
            System.out.println(x + " : " + y);
        }
    }

    private static void generateMysteryWildsInFG() {
        generateMysteryWildsInFG(pickMysteryWildsByReels());
    }

    private static void generateMysteryWildsInFG(final int[] mysteryWildsByReels) {

        System.out.println("=== Mystery Wilds in Free Game ===\n");
        System.out.println("MW on reels: " + Arrays.toString(mysteryWildsByReels) + "\n");

        final Set<Integer> pickedWilds = new HashSet<Integer>(MW_POSITIONS.length);

        int iteration = 0;
        while (size(mysteryWildsByReels) > 0) {

            System.out.println("----- Iteration " + ++iteration + " -----\n");

            // Mystery wilds in array
            int[] mysteryWilds = new int[0];
            for (int reelIndex = 0; reelIndex < mysteryWildsByReels.length; reelIndex++) {
                if (mysteryWildsByReels[reelIndex] > 0) {
                    final int[] wilds = new int[mysteryWildsByReels[reelIndex]];
                    Arrays.fill(wilds, reelIndex);
                    mysteryWilds = concat(mysteryWilds, wilds);
                }
            }
            System.out.println("Mystery wilds: " + Arrays.toString(mysteryWilds));

            // Pick uniformly one random wild
            final int mysteryWildIndex = Randoms.randInt(0, mysteryWilds.length);
            final int mysteryWildReel = mysteryWilds[mysteryWildIndex];

            // decrease number of mysteryWildsByReels
            mysteryWildsByReels[mysteryWildReel] = mysteryWildsByReels[mysteryWildReel] - 1;

            // Pick uniformly position on the reel
            final Set<Integer> wildsOnReel = new HashSet<Integer>(Arrays.asList(toObject(MW_POSITIONS_ON_REELS[mysteryWildReel])));
            wildsOnReel.removeAll(pickedWilds);
            final int position = wildsOnReel.toArray(new Integer[wildsOnReel.size()])[Randoms.randInt(0, wildsOnReel.size())];
            pickedWilds.add(position);

            System.out.println("Pivot: " + position);
            System.out.println("Picked: " + pickedWilds + "\n");

            // Put, if possible, adjacent wilds
            int[] adjacent = ADJACENT_POSITIONS[position];
            System.out.println("Adjacent: " + Arrays.toString(adjacent) + "\n");

            pickAdjacent(position, pickedWilds, mysteryWildsByReels);
        }
    }

    private static int size(final int[] mysteryWildsByReels) {
        int size = 0;
        for (final int wildsOnReel : mysteryWildsByReels) {
            size += wildsOnReel;
        }
        return size;
    }

    private static int[] pickMysteryWildsByReels() {
        final int[] mysteryWildsByReels = new int[scenario.length];
        for (int i = 0; i < scenario.length; i++) {
            mysteryWildsByReels[i] = RandomUtils.randomIndexX(scenario[i]);
        }
        return mysteryWildsByReels;
    }

    private static int[] pickMysteryWildsPositions(final int[] mysteryWildsByReels) {
        int[] positions = new int[0];
        for (int i = 0; i < scenario.length; i++) {
            positions = concat(positions, Arrays.copyOfRange(RandomUtils.shuffle(MW_POSITIONS_ON_REELS[i]), 0, mysteryWildsByReels[i]));
        }
        return positions;
    }

    private static Set<Integer> pickAdjacent(final int position, final Set<Integer> picked, final int[] availableWildsByReels) {

        final Set<Integer> result = new HashSet<Integer>();
        final Set<Integer> adjacent = new HashSet<Integer>(Arrays.asList(toObject(ADJACENT_POSITIONS[position])));
        final int x = position % NUM_REELS;

        pickAdjacentOnReel(picked, adjacent, availableWildsByReels, x - 1);
        pickAdjacentOnReel(picked, adjacent, availableWildsByReels, x);
        pickAdjacentOnReel(picked, adjacent, availableWildsByReels, x + 1);

        System.out.println(toString(picked.toArray(new Integer[picked.size()]), position));

        return result;
    }

    private static void pickAdjacentOnReel(final Set<Integer> picked, final Set<Integer> adjacent, final int[] availableWildsByReels, final int reelIndex) {
        if (reelIndex >= 0 && reelIndex < NUM_REELS) {
            final Set<Integer> pickFrom = new HashSet<Integer>(adjacent);
            final List<Integer> positionsOnReels = Arrays.asList(toObject(MW_POSITIONS_ON_REELS[reelIndex]));
            pickFrom.retainAll(positionsOnReels);
            pickFrom.removeAll(picked);
            System.out.println("Adjacent on " + reelIndex + " reel: " + pickFrom);

            final Iterator<Integer> iterator = pickFrom.iterator();
            while (iterator.hasNext() && availableWildsByReels[reelIndex] > 0) {
                final Integer position = iterator.next();
                iterator.remove();
                availableWildsByReels[reelIndex] = availableWildsByReels[reelIndex] - 1;
                picked.add(position);
            }

            System.out.println("Picked after " + reelIndex + " : " + picked + "\n");
        }
    }

    private static int[] concat(int[] a, int[] b) {
        int aLen = a.length;
        int bLen = b.length;
        int[] c = new int[aLen + bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }

    private static String toString(final int[] wilds) {
        return toString(toObject(wilds));
    }

    private static String toString(final int[] wilds, final Integer pivot) {
        return toString(toObject(wilds), pivot);
    }

    private static String toString(final Integer[] wilds) {
        return toString(wilds, null);
    }

    private static String toString(final Integer[] wildsArray, final Integer pivot) {
        final Integer[] wilds = pivot != null ? removeFromArray(wildsArray, pivot) : wildsArray;
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

    private static Integer[] removeFromArray(Integer[] values, final int valueToDelete) {
        List<Integer> result = new ArrayList<Integer>(values.length);
        for (final int value : values) {
            if (value != valueToDelete) {
                result.add(value);
            }
        }
        return result.toArray(new Integer[result.size()]);
    }

}
