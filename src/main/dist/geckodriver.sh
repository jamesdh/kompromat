#!/bin/bash
# download and install latest geckodriver for linux or mac.
# required for selenium to drive a firefox browser.

json=$(curl -s https://api.github.com/repos/mozilla/geckodriver/releases/latest)
if [[ $(uname) == "Darwin" ]]; then
  if [[ $(uname -m) == "x86_64" ]]; then
    url=$(echo "$json" | jq -r '.assets[].browser_download_url | select(contains("macos.tar"))')
  elif [[ $(uname -m) == "arm64" ]]; then
    url=$(echo "$json" | jq -r '.assets[].browser_download_url | select(contains("macos-aarch64.tar"))')
  fi
elif [[ $(uname) == "Linux" ]]; then
    url=$(echo "$json" | jq -r '.assets[].browser_download_url | select(contains("linux64"))')
else
    echo "can't determine OS"
    exit 1
fi
curl -s -L "$url" | tar -xz
chmod +x geckodriver
