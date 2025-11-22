#include <bits/stdc++.h>

using namespace std;

#include "2105160_symbol_table.hpp"

#define ENABLE_OUTPUT 0

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

    


    int bucketNumber;
    cin>>bucketNumber;
    cin.ignore();

    string line;

    //SDBM , DJB2 , FNV1A, MURMUR
    SymbolTable* symbolTable = new SymbolTable(bucketNumber,"SDBM");
    SymbolTable* stDJB2 = new SymbolTable(bucketNumber, "DJB2");
    SymbolTable* stFNV1A = new SymbolTable(bucketNumber,"FNV1A");
    SymbolTable* stMURMUR = new SymbolTable(bucketNumber,"MURMUR");

    int cmdCount = 1;

    int sdbmCollison,  DJB2Col,FNV1ACol,MMURMURCol;
   
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
                stDJB2->Insert(symbolName,symbolType);
                stFNV1A->Insert(symbolName,symbolType);
                stMURMUR->Insert(symbolName,symbolType);
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
                    SymbolInfo* refd=  stDJB2->LookUp(symbolName);
                    SymbolInfo* reff = stFNV1A->LookUp(symbolName);
                    SymbolInfo* refm = stMURMUR->LookUp(symbolName);
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
                    bool refd=  stDJB2->LookUp(symbolName);
                    bool reff = stFNV1A->LookUp(symbolName);
                    bool refm = stMURMUR->LookUp(symbolName);
          
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
                    stDJB2->EnterScope();
                    stFNV1A->EnterScope();
                    stMURMUR->EnterScope();
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
                    bool refd=  stDJB2->ExitScope();
                    bool reff = stFNV1A->ExitScope();
                    bool refm = stMURMUR->ExitScope();
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

                    sdbmCollison = symbolTable->getAvgCollision();
                    DJB2Col = stDJB2->getAvgCollision();
                    FNV1ACol = stFNV1A->getAvgCollision();
                    MMURMURCol = stMURMUR->getAvgCollision();
                    
                    freopen("report.txt","w",stdout);

                    cout<<"Quality of SDBM Hash Fuction: "<<((sdbmCollison*1.0)/(bucketNumber*1.0))/(1.0*(symbolTable->nextScopeId-1))<<endl;
                    cout<<"Quality of DJB2 Hash Fuction: "<<(DJB2Col/(bucketNumber*1.0))/(1.0*(symbolTable->nextScopeId-1))<<endl;
                    cout<<"Quality of FNV1A Hash Fuction: "<<(FNV1ACol/(bucketNumber*1.0))/(1.0*(symbolTable->nextScopeId-1))<<endl;
                    cout<<"Quality of Murmur Hash Fuction: "<<(MMURMURCol/(bucketNumber*1.0))/(1.0*(symbolTable->nextScopeId-1))<<endl;

                                    

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