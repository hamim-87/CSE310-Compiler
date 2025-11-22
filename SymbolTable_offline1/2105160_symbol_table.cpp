
#include "2105160_symbol_table.hpp"
#include <string>
#include <iostream>
using namespace std;


SymbolTable :: SymbolTable(int bucketNumberInScope, string hash) noexcept{
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
    ScopeTable* newScope = new ScopeTable(bucketNumberInScope, nextScopeId++);
    newScope->setParentScope(currentScope);
    newScope->setHashType(hash);
    currentScope = newScope;

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
        return false; // No scope to exit
    }

    ScopeTable* temp = currentScope;
    currentScope = temp->getParentScope();
    collison += temp->getCollision();

    int scopeid = temp->getScopeUid();
    delete temp;

    if (currentScope == nullptr) {
        if (flag) {
            cout << "\tScopeTable# " << scopeid << " removed" << endl;
        }
        return true; // Last scope removed
    } else {
        cout << "\tScopeTable# " << scopeid << " removed" << endl;
        return false;
    }
    


}

bool SymbolTable :: Insert(string symbolName,string symbolType) const noexcept{
    return currentScope->Insert(symbolName,symbolType);
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

void SymbolTable :: PrintCurrentScopeTable() noexcept{
    currentScope->print();
    
}

void SymbolTable :: PrintAllScopeTable() noexcept{
    ScopeTable* cur = currentScope;
    int tb = 1;
    while(cur != nullptr){
        cur->print(tb);
        tb++;
        cur = cur->getParentScope();
    }
}


int SymbolTable :: getAvgCollision() noexcept{
    ScopeTable* cur = currentScope;
    
    
    while(cur != nullptr){
        this->collison += cur->getCollision();
        
        cur = cur->getParentScope();
    }

    return this->collison;
}




