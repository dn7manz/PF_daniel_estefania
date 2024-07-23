// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract SimpleVoting {
    string[] public names;
    uint256[] public votes;

    function vote(string memory name) public {
        bool found = false;
        for (uint256 i = 0; i < names.length; i++) {
            if (keccak256(abi.encodePacked(names[i])) == keccak256(abi.encodePacked(name))) {
                votes[i]++;
                found = true;
                break;
            }
        }
        if (!found) {
            names.push(name);
            votes.push(1);
        }
    }

    function getResults() public view returns (string[] memory, uint256[] memory) {
        return (names, votes);
    }
}


