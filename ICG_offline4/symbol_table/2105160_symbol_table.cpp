
#include "2105160_symbol_table.hpp"
#include <string>
#include <iostream>
using namespace std;


SymbolTable::SymbolTable(int bucketNumberInScope, std::string hash, ostream& outputfile) noexcept
    : out(outputfile) 
{
    this->currentScope = nullptr;
    this->bucketNumberInScope = bucketNumberInScope;
    this->nextScopeId = 1;
    this->hash = hash;
    this->collison = 0;
    EnterScope();
    currentScope->setHashType(hash);
}


SymbolTable :: ~SymbolTable(){
    while(currentScope != nullptr){
        ScopeTable* temp = currentScope;
        currentScope = currentScope->getParentScope();
        delete temp;
    }
   
}

void SymbolTable :: EnterScope() noexcept{
    // ScopeTable* newScope = new ScopeTable(bucketNumberInScope,(nextScopeId++));
    // newScope->setParentScope(currentScope);
    // newScope->setHashType(hash);
    // currentScope = newScope;

    if(currentScope == nullptr) {

        ScopeTable* newScope = new ScopeTable(bucketNumberInScope,to_string(nextScopeId++));
        newScope->setParentScope(currentScope);
        newScope->setHashType(hash);
        currentScope = newScope;
    }else{
        int scope_number=currentScope->getChildScopeCount(); //new 
        currentScope->setChildScopeCount(scope_number+1); //new 
        string new_scope_id=currentScope->getScopeUid()+"."+to_string(scope_number+1); //new 
        ScopeTable *table = new ScopeTable(bucketNumberInScope,new_scope_id); //new 
        table->setParentScope(currentScope);
        currentScope = table;
    }

}

bool SymbolTable :: ExitScope(bool flag) noexcept{


    // ScopeTable* temp = currentScope;
    
    // collison += temp->getCollision();

    // if(temp->getParentScope() != nullptr ){
    //     int scopeid = currentScope->getScopeUid();
    //     currentScope = temp->getParentScope();
      

    //     temp->setParentScope(nullptr);
    //     delete temp;

    //     //ScopeTable# 2 removed
    //     cout<<"\tScopeTable# "<<scopeid<<" removed"<<endl;
    //     return false;
        
    // }else if(temp->getParentScope() == nullptr && flag){
    //     cout<<"\tScopeTable# "<<currentScope->getScopeUid()<<" removed";
    //     currentScope = nullptr;
    //     delete temp;
    //     return true;
    // }


    // return false;

    
    
    if (currentScope == nullptr) {
        return false; 
    }

    ScopeTable* temp = currentScope;
    currentScope = temp->getParentScope();
    collison += temp->getCollision();

    string scopeid = temp->getScopeUid();
    delete temp;

    if (currentScope == nullptr) {
        if (flag) {
            cout << "\tScopeTable# " << scopeid << " removed" << endl;
        }
        return true; 
    } else {
        cout << "\tScopeTable# " << scopeid << " removed" << endl;
        return false;
    }
    


}

bool SymbolTable :: InsertInParent(string symbolName,string symbolType,int ct, FunctionInfo* _functionInfo) const noexcept{

    ScopeTable *curr = this->currentScope;

    while(ct--){
        curr = curr->getParentScope();
    }
    if(curr->Insert(symbolName,symbolType,out,_functionInfo)){
        //PrintAllScopeTable();
        return true;
    }
    return false;
}

bool SymbolTable :: Insert(string symbolName,string symbolType) const noexcept{
    if(currentScope->Insert(symbolName,symbolType,out)){
        //PrintAllScopeTable();
        return true;
    }
    return false;
}

bool SymbolTable :: Remove(string symbolName) const noexcept {
    return currentScope->Delete(symbolName);
}

SymbolInfo* SymbolTable :: LookUp(string symbolName) const noexcept{
    for(auto scope = currentScope ; scope != nullptr;scope= scope->getParentScope()){
        auto newSymbolInfo = scope->LookUp(symbolName);
        
        if(newSymbolInfo != nullptr){
            return newSymbolInfo;
        }
    }
    //
    cout<<"'"<<symbolName<<"' not found in any of the ScopeTables"<<endl;
    return nullptr;
}

void SymbolTable :: PrintCurrentScopeTable() {
    currentScope->print();
    
}

void SymbolTable :: PrintAllScopeTable() const {
    ScopeTable* cur = currentScope;
    int tb = 1;
    while(cur != nullptr){
        bool can;
        cur->print(out,can); //
        tb++;
        cur = cur->getParentScope();

        if(can) continue;

        out<<endl<<endl<<endl;
    }
    //out<<endl;
}


int SymbolTable :: getAvgCollision() noexcept{
    ScopeTable* cur = currentScope;
    
    
    while(cur != nullptr){
        this->collison += cur->getCollision();
        
        cur = cur->getParentScope();
    }

    return this->collison;
}

string SymbolTable :: getScope() {
    return this->currentScope->getScopeUid();
}


