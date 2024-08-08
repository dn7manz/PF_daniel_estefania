package contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.5.3.
 */
@SuppressWarnings("rawtypes")
public class Contrato_sol_DAppEducativa extends Contract {
    public static final String BINARY = "6080604052348015600e575f80fd5b50600780546001600160a01b031916331790556123298061002e5f395ff3fe608060405234801561000f575f80fd5b506004361061011c575f3560e01c806392b95452116100a9578063cffa15b91161006e578063cffa15b9146102be578063e36d1c49146102d1578063e9ef462f146102e4578063eab1ad17146102f7578063f6b91ea914610318575f80fd5b806392b9545214610242578063a64ad39f14610257578063a78b7db81461026a578063c85bf7721461027d578063cd7bc88a1461029d575f80fd5b8063372552e0116100ef578063372552e0146101c05780635f3172bc146101d65780636b9d3954146101eb5780636ddd66621461020d5780638da5cb5b1461022f575f80fd5b806306d3e1c9146101205780631bb4a8ef146101505780632cce76d31461017057806331909c6414610185575b5f80fd5b61013361012e366004611a8f565b61033e565b6040516001600160a01b0390911681526020015b60405180910390f35b61016361015e366004611b5e565b610366565b6040516101479190611bfe565b6101786104b6565b6040516101479190611c17565b6101b0610193366004611d05565b6001600160a01b03165f9081526004602052604090205460ff1690565b6040519015158152602001610147565b6101c8610926565b604051610147929190611dbb565b6101de610ad5565b6040516101479190611de8565b6101b06101f9366004611d05565b60046020525f908152604090205460ff1681565b61022061021b366004611d05565b610ba9565b60405161014793929190611dfa565b600754610133906001600160a01b031681565b610255610250366004611e3c565b610d82565b005b610133610265366004611a8f565b610ed1565b610255610278366004611e86565b610ee0565b61029061028b366004611ed0565b611096565b6040516101479190611f09565b6102b06102ab366004611ed0565b6112a4565b604051610147929190611f1b565b6102556102cc366004611e3c565b61135a565b6102556102df366004611f44565b6114a1565b6101636102f2366004611a8f565b611665565b61030a610305366004611d05565b61170b565b604051610147929190612009565b61032b610326366004611d05565b6117af565b604051610147979695949392919061202c565b6005818154811061034d575f80fd5b5f918252602090912001546001600160a01b0316905081565b6001600160a01b0383165f90815260208181526040918290209151606092610390929091016120e0565b60405160208183030381529060405280519060200120836040516020016103b79190612151565b60405160208183030381529060405280519060200120146104135760405162461bcd60e51b8152602060048201526011602482015270436f7272656f20696e636f72726563746f60781b60448201526064015b60405180910390fd5b6001600160a01b0384165f908152602081815260409182902060010154915161043e91859101612151565b60405160208183030381529060405280519060200120146104935760405162461bcd60e51b815260206004820152600f60248201526e7061737320696e636f72726563746160881b604482015260640161040a565b506040805180820190915260048152637472756560e01b60208201529392505050565b335f9081526004602052604090205460609060ff1661052c5760405162461bcd60e51b815260206004820152602c60248201527f536f6c6f2070726f6665736f7265732070756564656e20656a6563757461722060448201526b32b9ba3090333ab731b4b7b760a11b606482015260840161040a565b6003545f906001600160401b0381111561054857610548611ac1565b6040519080825280602002602001820160405280156105bc57816020015b6105a96040518060e00160405280606081526020015f80191681526020016060815260200160608152602001606081526020015f15158152602001606081525090565b8152602001906001900390816105665790505b5090505f5b600354811015610920575f80600383815481106105e0576105e0612167565b5f9182526020808320909101546001600160a01b031683528201929092526040908101909120815160e0810190925280548290829061061e906120a8565b80601f016020809104026020016040519081016040528092919081815260200182805461064a906120a8565b80156106955780601f1061066c57610100808354040283529160200191610695565b820191905f5260205f20905b81548152906001019060200180831161067857829003601f168201915b50505050508152602001600182015481526020016002820180546106b8906120a8565b80601f01602080910402602001604051908101604052809291908181526020018280546106e4906120a8565b801561072f5780601f106107065761010080835404028352916020019161072f565b820191905f5260205f20905b81548152906001019060200180831161071257829003601f168201915b50505050508152602001600382018054610748906120a8565b80601f0160208091040260200160405190810160405280929190818152602001828054610774906120a8565b80156107bf5780601f10610796576101008083540402835291602001916107bf565b820191905f5260205f20905b8154815290600101906020018083116107a257829003601f168201915b505050505081526020016004820180546107d8906120a8565b80601f0160208091040260200160405190810160405280929190818152602001828054610804906120a8565b801561084f5780601f106108265761010080835404028352916020019161084f565b820191905f5260205f20905b81548152906001019060200180831161083257829003601f168201915b5050509183525050600582015460ff161515602082015260068201805460409092019161087b906120a8565b80601f01602080910402602001604051908101604052809291908181526020018280546108a7906120a8565b80156108f25780601f106108c9576101008083540402835291602001916108f2565b820191905f5260205f20905b8154815290600101906020018083116108d557829003601f168201915b50505050508152505082828151811061090d5761090d612167565b60209081029190910101526001016105c1565b50905090565b6060805f6005805490506001600160401b0381111561094757610947611ac1565b60405190808252806020026020018201604052801561097a57816020015b60608152602001906001900390816109655790505b5090505f5b600554811015610a6e5760015f6005838154811061099f5761099f612167565b5f9182526020808320909101546001600160a01b03168352820192909252604001902080546109cd906120a8565b80601f01602080910402602001604051908101604052809291908181526020018280546109f9906120a8565b8015610a445780601f10610a1b57610100808354040283529160200191610a44565b820191905f5260205f20905b815481529060010190602001808311610a2757829003601f168201915b5050505050828281518110610a5b57610a5b612167565b602090810291909101015260010161097f565b5060058181805480602002602001604051908101604052809291908181526020018280548015610ac557602002820191905f5260205f20905b81546001600160a01b03168152600190910190602001808311610aa7575b5050505050915092509250509091565b60606006805480602002602001604051908101604052809291908181526020015f905b82821015610ba0578382905f5260205f20018054610b15906120a8565b80601f0160208091040260200160405190810160405280929190818152602001828054610b41906120a8565b8015610b8c5780601f10610b6357610100808354040283529160200191610b8c565b820191905f5260205f20905b815481529060010190602001808311610b6f57829003601f168201915b505050505081526020019060010190610af8565b50505050905090565b6001600160a01b0381165f90815260208190526040902060028101805460609283928392600383019060048401908390610be2906120a8565b80601f0160208091040260200160405190810160405280929190818152602001828054610c0e906120a8565b8015610c595780601f10610c3057610100808354040283529160200191610c59565b820191905f5260205f20905b815481529060010190602001808311610c3c57829003601f168201915b50505050509250818054610c6c906120a8565b80601f0160208091040260200160405190810160405280929190818152602001828054610c98906120a8565b8015610ce35780601f10610cba57610100808354040283529160200191610ce3565b820191905f5260205f20905b815481529060010190602001808311610cc657829003601f168201915b50505050509150808054610cf6906120a8565b80601f0160208091040260200160405190810160405280929190818152602001828054610d22906120a8565b8015610d6d5780601f10610d4457610100808354040283529160200191610d6d565b820191905f5260205f20905b815481529060010190602001808311610d5057829003601f168201915b50505050509050935093509350509193909250565b6001600160a01b0382165f9081526020819052604090206005015460ff16610de45760405162461bcd60e51b81526020600482015260156024820152745573756172696f206e6f207265676973747261646f60581b604482015260640161040a565b6001600160a01b0382165f908152602081905260409020600281018054610e0a906120a8565b90505f03610e265760028101610e2083826121c6565b50505050565b806003018054610e35906120a8565b90505f03610e4b5760038101610e2083826121c6565b806004018054610e5a906120a8565b90505f03610e705760048101610e2083826121c6565b60405162461bcd60e51b815260206004820152602b60248201527f456c207573756172696f2079612065737461206d6174726963756c61646f206560448201526a3710199031bab939b7b99760a91b606482015260840161040a565b505050565b6003818154811061034d575f80fd5b6007546001600160a01b03163314610f0a5760405162461bcd60e51b815260040161040a90612280565b5f6001600160a01b0316600283604051610f249190612151565b908152604051908190036020019020600101546001600160a01b031614610f835760405162461bcd60e51b8152602060048201526013602482015272437572736f207961207265676973747261646f60681b604482015260640161040a565b6001600160a01b0381165f9081526004602052604090205460ff16610fea5760405162461bcd60e51b815260206004820152601e60248201527f456c2070726f6665736f72206e6f2065737461207265676973747261646f0000604482015260640161040a565b6040518060400160405280838152602001826001600160a01b03168152506002836040516110189190612151565b9081526040519081900360200190208151819061103590826121c6565b5060209190910151600191820180546001600160a01b0319166001600160a01b039092169190911790556006805491820181555f527ff652222313e28459528d920b65115c16c04f3efc82aaedc97be59f3f377c0d3f01610ecc83826121c6565b60605f805b60035481101561114a57836040516020016110b69190612151565b604051602081830303815290604052805190602001205f80600384815481106110e1576110e1612167565b5f9182526020808320909101546001600160a01b031683528281019390935260409182019020905161111992600290920191016120e0565b6040516020818303038152906040528051906020012003611142578161113e816122cf565b9250505b60010161109b565b505f816001600160401b0381111561116457611164611ac1565b60405190808252806020026020018201604052801561118d578160200160208202803683370190505b5090505f805b60035481101561129a57856040516020016111ae9190612151565b604051602081830303815290604052805190602001205f80600384815481106111d9576111d9612167565b5f9182526020808320909101546001600160a01b031683528281019390935260409182019020905161121192600290920191016120e0565b6040516020818303038152906040528051906020012003611292576003818154811061123f5761123f612167565b905f5260205f20015f9054906101000a90046001600160a01b031683838151811061126c5761126c612167565b6001600160a01b03909216602092830291909101909101528161128e816122cf565b9250505b600101611193565b5090949350505050565b80516020818301810180516002825292820191909301209152805481906112ca906120a8565b80601f01602080910402602001604051908101604052809291908181526020018280546112f6906120a8565b80156113415780601f1061131857610100808354040283529160200191611341565b820191905f5260205f20905b81548152906001019060200180831161132457829003601f168201915b505050600190930154919250506001600160a01b031682565b6007546001600160a01b031633146113845760405162461bcd60e51b815260040161040a90612280565b6001600160a01b0382165f908152600160208190526040909120015460ff16156113e95760405162461bcd60e51b815260206004820152601660248201527550726f6665736f72207961207265676973747261646f60501b604482015260640161040a565b604080518082018252828152600160208083018290526001600160a01b0386165f908152919052919091208151819061142290826121c6565b506020918201516001918201805491151560ff199283161790556001600160a01b039094165f818152600490935260408320805490951682179094556005805491820181559091527f036b6384b5eca791c62761152d0c79bb0604c104a5fb6f4eb0703f3154bb3db00180546001600160a01b03191690921790915550565b6001600160a01b0385165f9081526020819052604090206005015460ff16156115045760405162461bcd60e51b81526020600482015260156024820152745573756172696f207961207265676973747261646f60581b604482015260640161040a565b6040518060e00160405280858152602001846040516020016115269190612151565b60408051601f1981840301815291815281516020928301208352828201869052805180830182525f808252848301919091528151808401835281815260608501526001608085015260a09093018590526001600160a01b03891683529082905290208151819061159690826121c6565b5060208201516001820155604082015160028201906115b590826121c6565b50606082015160038201906115ca90826121c6565b50608082015160048201906115df90826121c6565b5060a082015160058201805460ff191691151591909117905560c0820151600682019061160c90826121c6565b5050600380546001810182555f919091527fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85b0180546001600160a01b0319166001600160a01b0397909716969096179095555050505050565b60068181548110611674575f80fd5b905f5260205f20015f91509050805461168c906120a8565b80601f01602080910402602001604051908101604052809291908181526020018280546116b8906120a8565b80156117035780601f106116da57610100808354040283529160200191611703565b820191905f5260205f20905b8154815290600101906020018083116116e657829003601f168201915b505050505081565b60016020525f9081526040902080548190611725906120a8565b80601f0160208091040260200160405190810160405280929190818152602001828054611751906120a8565b801561179c5780601f106117735761010080835404028352916020019161179c565b820191905f5260205f20905b81548152906001019060200180831161177f57829003601f168201915b5050506001909301549192505060ff1682565b5f602081905290815260409020805481906117c9906120a8565b80601f01602080910402602001604051908101604052809291908181526020018280546117f5906120a8565b80156118405780601f1061181757610100808354040283529160200191611840565b820191905f5260205f20905b81548152906001019060200180831161182357829003601f168201915b50505050509080600101549080600201805461185b906120a8565b80601f0160208091040260200160405190810160405280929190818152602001828054611887906120a8565b80156118d25780601f106118a9576101008083540402835291602001916118d2565b820191905f5260205f20905b8154815290600101906020018083116118b557829003601f168201915b5050505050908060030180546118e7906120a8565b80601f0160208091040260200160405190810160405280929190818152602001828054611913906120a8565b801561195e5780601f106119355761010080835404028352916020019161195e565b820191905f5260205f20905b81548152906001019060200180831161194157829003601f168201915b505050505090806004018054611973906120a8565b80601f016020809104026020016040519081016040528092919081815260200182805461199f906120a8565b80156119ea5780601f106119c1576101008083540402835291602001916119ea565b820191905f5260205f20905b8154815290600101906020018083116119cd57829003601f168201915b5050506005840154600685018054949560ff909216949193509150611a0e906120a8565b80601f0160208091040260200160405190810160405280929190818152602001828054611a3a906120a8565b8015611a855780601f10611a5c57610100808354040283529160200191611a85565b820191905f5260205f20905b815481529060010190602001808311611a6857829003601f168201915b5050505050905087565b5f60208284031215611a9f575f80fd5b5035919050565b80356001600160a01b0381168114611abc575f80fd5b919050565b634e487b7160e01b5f52604160045260245ffd5b5f82601f830112611ae4575f80fd5b81356001600160401b03811115611afd57611afd611ac1565b604051601f8201601f19908116603f011681016001600160401b0381118282101715611b2b57611b2b611ac1565b604052818152838201602001851015611b42575f80fd5b816020850160208301375f918101602001919091529392505050565b5f805f60608486031215611b70575f80fd5b611b7984611aa6565b925060208401356001600160401b03811115611b93575f80fd5b611b9f86828701611ad5565b92505060408401356001600160401b03811115611bba575f80fd5b611bc686828701611ad5565b9150509250925092565b5f81518084528060208401602086015e5f602082860101526020601f19601f83011685010191505092915050565b602081525f611c106020830184611bd0565b9392505050565b5f602082016020835280845180835260408501915060408160051b8601019250602086015f5b82811015611cf957603f198786030184528151805160e08752611c6360e0880182611bd0565b90506020820151602088015260408201518782036040890152611c868282611bd0565b91505060608201518782036060890152611ca08282611bd0565b91505060808201518782036080890152611cba8282611bd0565b91505060a0820151151560a088015260c0820151915086810360c0880152611ce28183611bd0565b965050506020938401939190910190600101611c3d565b50929695505050505050565b5f60208284031215611d15575f80fd5b611c1082611aa6565b5f8151808452602084019350602083015f5b82811015611d575781516001600160a01b0316865260209586019590910190600101611d30565b5093949350505050565b5f82825180855260208501945060208160051b830101602085015f5b83811015611daf57601f19858403018852611d99838351611bd0565b6020988901989093509190910190600101611d7d565b50909695505050505050565b604081525f611dcd6040830185611d1e565b8281036020840152611ddf8185611d61565b95945050505050565b602081525f611c106020830184611d61565b606081525f611e0c6060830186611bd0565b8281036020840152611e1e8186611bd0565b90508281036040840152611e328185611bd0565b9695505050505050565b5f8060408385031215611e4d575f80fd5b611e5683611aa6565b915060208301356001600160401b03811115611e70575f80fd5b611e7c85828601611ad5565b9150509250929050565b5f8060408385031215611e97575f80fd5b82356001600160401b03811115611eac575f80fd5b611eb885828601611ad5565b925050611ec760208401611aa6565b90509250929050565b5f60208284031215611ee0575f80fd5b81356001600160401b03811115611ef5575f80fd5b611f0184828501611ad5565b949350505050565b602081525f611c106020830184611d1e565b604081525f611f2d6040830185611bd0565b905060018060a01b03831660208301529392505050565b5f805f805f60a08688031215611f58575f80fd5b611f6186611aa6565b945060208601356001600160401b03811115611f7b575f80fd5b611f8788828901611ad5565b94505060408601356001600160401b03811115611fa2575f80fd5b611fae88828901611ad5565b93505060608601356001600160401b03811115611fc9575f80fd5b611fd588828901611ad5565b92505060808601356001600160401b03811115611ff0575f80fd5b611ffc88828901611ad5565b9150509295509295909350565b604081525f61201b6040830185611bd0565b905082151560208301529392505050565b60e081525f61203e60e083018a611bd0565b88602084015282810360408401526120568189611bd0565b9050828103606084015261206a8188611bd0565b9050828103608084015261207e8187611bd0565b905084151560a084015282810360c084015261209a8185611bd0565b9a9950505050505050505050565b600181811c908216806120bc57607f821691505b6020821081036120da57634e487b7160e01b5f52602260045260245ffd5b50919050565b5f8083546120ed816120a8565b600182168015612104576001811461211957612146565b60ff1983168652811515820286019350612146565b865f5260205f205f5b8381101561213e57815488820152600190910190602001612122565b505081860193505b509195945050505050565b5f82518060208501845e5f920191825250919050565b634e487b7160e01b5f52603260045260245ffd5b601f821115610ecc57805f5260205f20601f840160051c810160208510156121a05750805b601f840160051c820191505b818110156121bf575f81556001016121ac565b5050505050565b81516001600160401b038111156121df576121df611ac1565b6121f3816121ed84546120a8565b8461217b565b6020601f821160018114612225575f831561220e5750848201515b5f19600385901b1c1916600184901b1784556121bf565b5f84815260208120601f198516915b828110156122545787850151825560209485019460019092019101612234565b508482101561227157868401515f19600387901b60f8161c191681555b50505050600190811b01905550565b6020808252602f908201527f536f6c6f20656c2070726f706965746172696f20707565646520656a6563757460408201526e30b91032b9ba3090333ab731b4b7b760891b606082015260800190565b5f600182016122ec57634e487b7160e01b5f52601160045260245ffd5b506001019056fea2646970667358221220bb733d1bcb5666fe478ed77b394c76f9dc45bded225646af1170550ad98f6d6264736f6c634300081a0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_AGREGARCURSO = "agregarCurso";

    public static final String FUNC_AGREGARPROFESOR = "agregarProfesor";

    public static final String FUNC_CUENTASPROFESORES = "cuentasProfesores";

    public static final String FUNC_CUENTASREGISTRADAS = "cuentasRegistradas";

    public static final String FUNC_CURSOS = "cursos";

    public static final String FUNC_ESPROFESOR = "esProfesor";

    public static final String FUNC_ESPROFESORREGISTRADO = "esProfesorRegistrado";

    public static final String FUNC_INICIARSESION = "iniciarSesion";

    public static final String FUNC_MATRICULARSEENOTROCURSO = "matricularseEnOtroCurso";

    public static final String FUNC_NOMBRESCURSOS = "nombresCursos";

    public static final String FUNC_OBTENERCUENTASPROFESORES = "obtenerCuentasProfesores";

    public static final String FUNC_OBTENERCURSOSMATRICULADOS = "obtenerCursosMatriculados";

    public static final String FUNC_OBTENERNOMBRESCURSOS = "obtenerNombresCursos";

    public static final String FUNC_OBTENERUSUARIOSPORCURSO = "obtenerUsuariosPorCurso";

    public static final String FUNC_OBTENERUSUARIOSREGISTRADOS = "obtenerUsuariosRegistrados";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PROFESORES = "profesores";

    public static final String FUNC_REGISTRARUSUARIO = "registrarUsuario";

    public static final String FUNC_USUARIOS = "usuarios";

    @Deprecated
    protected Contrato_sol_DAppEducativa(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Contrato_sol_DAppEducativa(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Contrato_sol_DAppEducativa(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Contrato_sol_DAppEducativa(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> agregarCurso(String _nombreCurso, String _profesor) {
        final Function function = new Function(
                FUNC_AGREGARCURSO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_nombreCurso), 
                new org.web3j.abi.datatypes.Address(160, _profesor)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> agregarProfesor(String _cuenta, String _nombre) {
        final Function function = new Function(
                FUNC_AGREGARPROFESOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _cuenta), 
                new org.web3j.abi.datatypes.Utf8String(_nombre)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> cuentasProfesores(BigInteger param0) {
        final Function function = new Function(FUNC_CUENTASPROFESORES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> cuentasRegistradas(BigInteger param0) {
        final Function function = new Function(FUNC_CUENTASREGISTRADAS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple2<String, String>> cursos(String param0) {
        final Function function = new Function(FUNC_CURSOS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        return new RemoteFunctionCall<Tuple2<String, String>>(function,
                new Callable<Tuple2<String, String>>() {
                    @Override
                    public Tuple2<String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Boolean> esProfesor(String param0) {
        final Function function = new Function(FUNC_ESPROFESOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> esProfesorRegistrado(String _cuenta) {
        final Function function = new Function(FUNC_ESPROFESORREGISTRADO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _cuenta)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> iniciarSesion(String _cuenta, String _correo, String _password) {
        final Function function = new Function(FUNC_INICIARSESION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _cuenta), 
                new org.web3j.abi.datatypes.Utf8String(_correo), 
                new org.web3j.abi.datatypes.Utf8String(_password)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> matricularseEnOtroCurso(String _cuenta, String _nuevoCurso) {
        final Function function = new Function(
                FUNC_MATRICULARSEENOTROCURSO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _cuenta), 
                new org.web3j.abi.datatypes.Utf8String(_nuevoCurso)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> nombresCursos(BigInteger param0) {
        final Function function = new Function(FUNC_NOMBRESCURSOS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple2<List<String>, List<String>>> obtenerCuentasProfesores() {
        final Function function = new Function(FUNC_OBTENERCUENTASPROFESORES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteFunctionCall<Tuple2<List<String>, List<String>>>(function,
                new Callable<Tuple2<List<String>, List<String>>>() {
                    @Override
                    public Tuple2<List<String>, List<String>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<List<String>, List<String>>(
                                convertToNative((List<Address>) results.get(0).getValue()), 
                                convertToNative((List<Utf8String>) results.get(1).getValue()));
                    }
                });
    }

    public RemoteFunctionCall<Tuple3<String, String, String>> obtenerCursosMatriculados(String _cuenta) {
        final Function function = new Function(FUNC_OBTENERCURSOSMATRICULADOS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _cuenta)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple3<String, String, String>>(function,
                new Callable<Tuple3<String, String, String>>() {
                    @Override
                    public Tuple3<String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<List> obtenerNombresCursos() {
        final Function function = new Function(FUNC_OBTENERNOMBRESCURSOS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<List> obtenerUsuariosPorCurso(String _curso) {
        final Function function = new Function(FUNC_OBTENERUSUARIOSPORCURSO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_curso)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<List> obtenerUsuariosRegistrados() {
        final Function function = new Function(FUNC_OBTENERUSUARIOSREGISTRADOS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Usuario>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple2<String, Boolean>> profesores(String param0) {
        final Function function = new Function(FUNC_PROFESORES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple2<String, Boolean>>(function,
                new Callable<Tuple2<String, Boolean>>() {
                    @Override
                    public Tuple2<String, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, Boolean>(
                                (String) results.get(0).getValue(), 
                                (Boolean) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> registrarUsuario(String _cuenta, String _correo, String _password, String _curso, String _nombre) {
        final Function function = new Function(
                FUNC_REGISTRARUSUARIO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _cuenta), 
                new org.web3j.abi.datatypes.Utf8String(_correo), 
                new org.web3j.abi.datatypes.Utf8String(_password), 
                new org.web3j.abi.datatypes.Utf8String(_curso), 
                new org.web3j.abi.datatypes.Utf8String(_nombre)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple7<String, byte[], String, String, String, Boolean, String>> usuarios(String param0) {
        final Function function = new Function(FUNC_USUARIOS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple7<String, byte[], String, String, String, Boolean, String>>(function,
                new Callable<Tuple7<String, byte[], String, String, String, Boolean, String>>() {
                    @Override
                    public Tuple7<String, byte[], String, String, String, Boolean, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<String, byte[], String, String, String, Boolean, String>(
                                (String) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (Boolean) results.get(5).getValue(), 
                                (String) results.get(6).getValue());
                    }
                });
    }

    @Deprecated
    public static Contrato_sol_DAppEducativa load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contrato_sol_DAppEducativa(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Contrato_sol_DAppEducativa load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contrato_sol_DAppEducativa(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Contrato_sol_DAppEducativa load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Contrato_sol_DAppEducativa(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Contrato_sol_DAppEducativa load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Contrato_sol_DAppEducativa(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Contrato_sol_DAppEducativa> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Contrato_sol_DAppEducativa.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    public static RemoteCall<Contrato_sol_DAppEducativa> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Contrato_sol_DAppEducativa.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Contrato_sol_DAppEducativa> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Contrato_sol_DAppEducativa.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Contrato_sol_DAppEducativa> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Contrato_sol_DAppEducativa.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class Usuario extends DynamicStruct {
        public String correo;

        public byte[] hashedPassword;

        public String cursoMatriculado;

        public String cursoMatriculado2;

        public String cursoMatriculado3;

        public Boolean registrado;

        public String nombre;

        public Usuario(String correo, byte[] hashedPassword, String cursoMatriculado, String cursoMatriculado2, String cursoMatriculado3, Boolean registrado, String nombre) {
            super(new org.web3j.abi.datatypes.Utf8String(correo), 
                    new org.web3j.abi.datatypes.generated.Bytes32(hashedPassword), 
                    new org.web3j.abi.datatypes.Utf8String(cursoMatriculado), 
                    new org.web3j.abi.datatypes.Utf8String(cursoMatriculado2), 
                    new org.web3j.abi.datatypes.Utf8String(cursoMatriculado3), 
                    new org.web3j.abi.datatypes.Bool(registrado), 
                    new org.web3j.abi.datatypes.Utf8String(nombre));
            this.correo = correo;
            this.hashedPassword = hashedPassword;
            this.cursoMatriculado = cursoMatriculado;
            this.cursoMatriculado2 = cursoMatriculado2;
            this.cursoMatriculado3 = cursoMatriculado3;
            this.registrado = registrado;
            this.nombre = nombre;
        }

        public Usuario(Utf8String correo, Bytes32 hashedPassword, Utf8String cursoMatriculado, Utf8String cursoMatriculado2, Utf8String cursoMatriculado3, Bool registrado, Utf8String nombre) {
            super(correo, hashedPassword, cursoMatriculado, cursoMatriculado2, cursoMatriculado3, registrado, nombre);
            this.correo = correo.getValue();
            this.hashedPassword = hashedPassword.getValue();
            this.cursoMatriculado = cursoMatriculado.getValue();
            this.cursoMatriculado2 = cursoMatriculado2.getValue();
            this.cursoMatriculado3 = cursoMatriculado3.getValue();
            this.registrado = registrado.getValue();
            this.nombre = nombre.getValue();
        }
    }
}
