#!/bin/bash

# Usage: ./run_driver_live_tracking_simulation.sh CONFIRMATION_NUMBER LAST_NAME [SPEEDUP]

if [ $# -lt 2 ]; then
  echo "Usage: $0 CONFIRMATION_NUMBER LAST_NAME [SPEEDUP]"
  echo "  CONFIRMATION_NUMBER: required"
  echo "  LAST_NAME:           required"
  echo "  SPEEDUP:             optional, defaults to 1"
  exit 1
fi

CONFIRMATION_NUMBER=$1
LAST_NAME=$2
SPEEDUP=${3:-1}   # if $3 is empty, default to 1

curl -X POST -H "Content-Type: application/json" \
  -d "{\"confirmation_number\":\"$CONFIRMATION_NUMBER\",\"last_name\":\"$LAST_NAME\",\"speedup\":$SPEEDUP}" \
  https://tracking-staging.mozio.com/simulate-driver-assignment-ride-tracking