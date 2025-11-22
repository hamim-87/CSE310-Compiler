#include <bits/stdc++.h>

using namespace std;

#include "2105160_symbol_table.hpp"



int main(int argc, char *argv[]){
    string inputFile;
    string hash = "SDBM";

    if(argc <= 1){
        cout<<"Please give input file and hash function"<<endl;
        return 1;
    }

    inputFile = argv[1];
    if(argc>2){
        hash = argv[2];
    }


    if(hash == "SDBM" || hash == "DJB2" || hash== "FNV1A" || hash == "MURMUR") cout<<"hash okay."<<endl;
    else{
        cout<<"give hash among 1.SDBM 2.DJB2 3.FNV1A 4.MURMUR"<<endl;
        return 1;
    }
    
    

    freopen(inputFile.c_str(), "r",stdin);
    freopen("output.txt","w",stdout);

    int bucketNumber;
    cin>>bucketNumber;
    cin.ignore();

    string line;

    SymbolTable* symbolTable = new SymbolTable(bucketNumber,hash);

    int cmdCount = 1;
   
    while(getline(cin,line)){
        if(!line.empty()){     
            cout<<"Cmd "<<cmdCount<<": "<<line<<endl;

            char command = line[0];

            stringstream string_stream(line);


            if(command == 'I' || command == 'L' || command == 'D')
            {
                cout<<"\t";
            }

            if(command == 'I'){
                int pos = 0;
                string temp;
                string symbolName = "";
                string symbolType = "";
                string checkType;
                while(string_stream >> temp){
                    //cout<<temp<<" "<<pos<<endl;
                    if(pos == 1){
                        symbolName+=temp;
                    }else if(pos==2){
                        symbolType +=temp;
                        checkType=temp;
                       
                    }else if(pos>2){
                        

                        if(checkType == "FUNCTION"){
                            
                            if(pos == 3){
                                symbolType +=",";
                                symbolType+=temp;
                                symbolType+="<==(";
                            }else{
                                symbolType+=temp;
                                symbolType +=",";
                            }
                        }else{
                            if(pos == 3){
                                symbolType+=",{(";
                                symbolType+=temp;
                                symbolType +=",";

                            }else{
                                if(pos%2 == 0){
                                    symbolType+=temp;
                                    symbolType+="),(";

                                }else{
                                    symbolType+=temp;
                                    symbolType += ",";
                                }
                            }

                        }
                    }
                    pos++;
                }
                if(checkType == "FUNCTION"){
                    string typeTemp = "";
                    for(int i =0;i<symbolType.size()-1;i++){
                        typeTemp+=symbolType[i];
                    }
                    typeTemp +=")";
                    symbolType = typeTemp;
                }else if(checkType == "STRUCT" || checkType == "UNION")
                {
                    string typeTemp = "";
                    for(int i =0;i<symbolType.size()-2;i++){
                        typeTemp+=symbolType[i];
                    }
                    typeTemp +="}";
                    symbolType = typeTemp;
                }


                symbolTable->Insert(symbolName,symbolType);
            }else if(command == 'L'){

                int pos = 0;
                string symbolName;
                string temp;

                while(string_stream >> temp){
                    if(pos == 1){
                        symbolName = temp;
                    }
                    pos++;
                }

                if(pos == 2){
                    SymbolInfo* ref = symbolTable->LookUp(symbolName);
                }else{
                    cout<<"Number of parameters mismatch for the command L"<<endl;
                }

                
            }else if(command == 'D'){
                int pos = 0;
                string symbolName;
                string temp;

                while(string_stream >> temp){
                    if(pos == 1){
                        symbolName = temp;
                    }
                    pos++;
                }

                if(pos == 2){
                    bool ref = symbolTable->Remove(symbolName);
                }else{
                    cout<<"Number of parameters mismatch for the command D"<<endl;
                }
            }else if(command == 'S'){
                int pos = 0;
                string temp;

                while(string_stream >> temp){
                    
                    pos++;
                }

                if(pos == 1){
                    symbolTable->EnterScope();
                }else{
                    cout<<"Number of parameters mismatch for the command S"<<endl;
                }
            }else if(command == 'E'){
                int pos = 0;
                string temp;

                while(string_stream >> temp){
                    
                    pos++;
                }

                if(pos == 1){
                    auto ref = symbolTable->ExitScope();
                }else{
                    cout<<"Number of parameters mismatch for the command E"<<endl;
                }
            }else if(command == 'Q'){
                int pos = 0;
                string temp;

                while(string_stream >> temp){
                    
                    pos++;
                }

                if(pos == 1){
                    
                    for(int i=1;i<symbolTable->nextScopeId;i++){
                        auto ref = symbolTable->ExitScope(true);
                        if(ref) break;
                    }
                    delete symbolTable;
                    break;
                }else{
                    cout<<"Number of parameters mismatch for the command Q"<<endl;
                }
            }else if(command == 'P'){
                int pos = 0;
                string symbol;
                string temp;

                while(string_stream >> temp){
                    if(pos == 1){
                        symbol = temp;
                    }
                    pos++;
                }

                if(pos == 2){
                    if(symbol == "C"){
                        symbolTable->PrintCurrentScopeTable();
                    }else if(symbol == "A"){
                        symbolTable->PrintAllScopeTable();
                    }else{
                        cout<<"Invalid second command"<<endl;
                    }
                }else{
                    cout<<"Number of parameters mismatch for the command P"<<endl;
                }
            }

            



            cmdCount++;
        }
    }
    
    


   
    
}