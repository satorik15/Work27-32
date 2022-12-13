package Work32;
public class JohnsonTrotter {

    public void generatePermute(int N) {
        ModifiedInteger[] permute = new ModifiedInteger[N];
        int noOfPermutation = 0;
        for (int i = 0; i < N; i++) {
            permute[i] = new ModifiedInteger(i, i + 1, true);
        }
        System.out.print(++noOfPermutation + " ");
        for (ModifiedInteger element : permute) {
            System.out.print(element.value + ",");
        }
        System.out.println();
        ModifiedInteger mobile;
        while ((mobile = largestMobile(permute)) != null) {
            //System.out.println("Largest Mobile is val- "+mobile.value+" index is "+mobile.index);
            swap(mobile, permute);
            //System.out.println("After Swap Largest Mobile is val- "+mobile.value+" index is "+mobile.index);
            reverse(permute, mobile);
            System.out.print(++noOfPermutation + " ");
            for (ModifiedInteger element : permute) {
                System.out.print(element.value + ",");
            }
            System.out.println();
        }

    }

    public void reverse(ModifiedInteger[] sequence, ModifiedInteger largestMobile) {
        for (ModifiedInteger element : sequence) {
            if (element.value > largestMobile.value) {
                element.direction = !element.direction;

            }

        }
    }

    public void swap(ModifiedInteger largestMobileInteger, ModifiedInteger[] sequence) {
        ModifiedInteger temp = largestMobileInteger;
        if (largestMobileInteger.direction) {
            sequence[largestMobileInteger.index] = sequence[largestMobileInteger.index - 1];
            sequence[largestMobileInteger.index - 1] = temp;
            sequence[largestMobileInteger.index].index += 1;
            largestMobileInteger.index -= 1;
        } else {
            sequence[largestMobileInteger.index] = sequence[largestMobileInteger.index + 1];

            sequence[largestMobileInteger.index + 1] = temp;
            sequence[largestMobileInteger.index].index -= 1;
            largestMobileInteger.index += 1;
        }


    }

    public ModifiedInteger largestMobile(ModifiedInteger[] sequence) {
        ModifiedInteger largestMobile = null;
        int count = 0;
        for (ModifiedInteger element : sequence) {
            if (element.direction && count != 0 && element.value > sequence[count - 1].value) {
                if (largestMobile == null) {
                    largestMobile = element;
                } else if (largestMobile.value < element.value) {
                    largestMobile = element;
                }

            } else if (!element.direction && count < sequence.length - 1 && element.value > sequence[count + 1].value) {
                if (largestMobile == null) {
                    largestMobile = element;
                } else if (largestMobile.value < element.value) {
                    largestMobile = element;
                }

            }
            count++;
        }

        return largestMobile;


    }

    //boolean direction-left-true;right-false
    public class ModifiedInteger {
        int value;
        int index;
        private boolean direction;

        ModifiedInteger(int index, int value, boolean direction) {
            this.index = index;
            this.value = value;
            this.direction = direction;
        }

        public boolean getDirection() {
            return direction;
        }

        public void setDirection(boolean direction) {
            this.direction = direction;
        }
    }

    public static void main(String args[])
    {

        JohnsonTrotter obj = new JohnsonTrotter();
        obj.generatePermute(5);
    }
}
