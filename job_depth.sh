#!/bin/bash

mode="D"
delay=2000

for nr in 0 1 2 3 4 5 6 7 8 9 10 11 12; do
    java App $mode $nr $delay
done

exit