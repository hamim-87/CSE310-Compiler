#include "2105160_scope_table.hpp"
#include <iostream>
#include <string>

using namespace std;

ScopeTable :: ScopeTable(const int _bucketNUmber, const string _scopeUid,ScopeTable * _parentScope, string hashType) noexcept {
    this->bucketNumber = _bucketNUmber;
    this->parentScope = _parentScope;
    this->scopeUid = _scopeUid;
    this->buckets = new SymbolInfo* [_bucketNUmber];
    this->collision = 0;

    this->child_scope_count = 0; //new

    for(int i = 0;i<_bucketNUmber;i++){
        buckets[i] = nullptr;
    }

    //print
    cout<<"\tScopeTable# "<<_scopeUid<<" created"<<endl;

    if(hashType == "SDBM") type = SDBM;
    else if(hashType == "DJB2")type =  DJB2;
    else if(hashType == "FNV1A") type =  FNV1A;
    else if(hashType == "MURMUR") type = MURMUR;
    else type = SDBM;


}

ScopeTable::~ScopeTable() noexcept{


    for(int i =0;i<bucketNumber;i++){
        SymbolInfo* temp = buckets[i];
        while(temp != nullptr){
            SymbolInfo* temp2 = temp;
            temp = temp->getNextSymbolInfo();
            delete temp2;
        }

        
        
    }
    delete[] buckets;
}


//getters

[[nodiscard]] int ScopeTable :: getBucketNumber() const noexcept{

    return this->bucketNumber;
}

[[nodiscard]] ScopeTable* ScopeTable :: getParentScope()  noexcept{
    return this->parentScope;
}

// [[nodiscard]] int ScopeTable :: getScopeUid() const noexcept{
//     return this->scopeUid;
// }

[[nodiscard]] string ScopeTable :: getScopeUid() const noexcept{ //new
    return this->scopeUid;
}

[[nodiscard]] int ScopeTable :: getCollision()  noexcept{
    return this->collision;
}

int ScopeTable :: getChildScopeCount() {
    return this->child_scope_count;
}

//setters

void ScopeTable :: setBucketNumber(int _bucketNumber) noexcept{
    this->bucketNumber = _bucketNumber;
}

void ScopeTable :: setParentScope(ScopeTable* _parentScope) noexcept{
    this->parentScope = _parentScope;
}

void ScopeTable :: setScopeUid(int _scopeUid) noexcept {
    this->scopeUid = _scopeUid;
}

void ScopeTable :: setHashType(string hashType) noexcept{
    if(hashType == "SDBM") type = SDBM;
    else if(hashType == "DJB2")type =  DJB2;
    else if(hashType == "FNV1A") type =  FNV1A;
    else if(hashType == "MURMUR") type = MURMUR;
    else type = SDBM;
}

void ScopeTable :: setChildScopeCount(int ct){ //new
    this->child_scope_count = ct;
}

//main functions
bool ScopeTable :: Insert(string symbolName, string symbolType, ostream& out,FunctionInfo* _functionInfo) noexcept{
    int index = getHash(symbolName,this->bucketNumber);


    SymbolInfo *current = buckets[index];
    SymbolInfo *previous = nullptr;
    int pos = 0;

    while(current!=nullptr){

        if(current->getSymbolName() == symbolName){
            //
          
            //out<<"< "<<symbolName<<" : "<<current->getSymbolType()<<" >"<<" already exists in ScopeTable# "<<this->scopeUid<<" at position "<<index<<", "<<pos<<endl<<endl;
            collision++;
            return false;
        }
        previous = current;
        current = current->getNextSymbolInfo();
        pos++;
    }


    

    if(buckets[index] == nullptr){

        //
        cout<<"Inserted in ScopeTable# "<<scopeUid<<" at position "<<(index+1)<<", "<<"1"<<endl;
        
        buckets[index] =new SymbolInfo(symbolName, symbolType,nullptr,_functionInfo);
        
        
        return true;
    }else{
        this->collision++;


        previous->setNextSymbolInfo(new SymbolInfo(symbolName, symbolType,nullptr,_functionInfo));

        cout<<"Inserted in ScopeTable# "<<scopeUid<<" at position "<<index+1<<", "<<pos+1<<endl;



        return true;


    }

    
}

SymbolInfo* ScopeTable :: LookUp(string symbolName) const noexcept {
    unsigned long long  index = getHash(symbolName,this->bucketNumber);

    if(buckets[index] == nullptr){
        // //
        // cout<<"'"<<symbolName<<"' not found in any of the ScopeTables"<<endl;

        return nullptr;
    }

    SymbolInfo* current = buckets[index];

    int pos = 1;
    while(current != nullptr){
        if(current->getSymbolName() == symbolName){

            //
            cout<<"'"<<symbolName<<"'"<<" found in ScopeTable# "<<scopeUid<<" at position "<<index+1<<", "<<pos<<endl;
            return current;
        }
        current = current->getNextSymbolInfo();
        pos++;
    }

    // //
    // cout<<"'"<<symbolName<<"' not found in any of the ScopeTables"<<endl;
    return nullptr;
}

bool ScopeTable :: Delete(string symbolName) const noexcept{
    long long index = getHash(symbolName,this->bucketNumber);
    
    SymbolInfo* current = buckets[index];
    SymbolInfo* previous = nullptr;

    int pos = 1;
    while(current != nullptr){
        if(current->getSymbolName() == symbolName){
            if(previous == nullptr){
                buckets[index] = current->getNextSymbolInfo();
            }else{
                previous->setNextSymbolInfo(current->getNextSymbolInfo());
            }
            delete current;
            //Deleted '==' from ScopeTable# 5 at position 2, 1
            cout<<"Deleted '"<<symbolName<<"' from ScopeTable# "<<scopeUid<<" at position "<<index+1<<", "<<pos<<endl;
            return true;
        }
        previous = current;
        current = current->getNextSymbolInfo();
        pos++;
    }

    cout<<"Not found in the current ScopeTable"<<endl;
    return false;
}

void ScopeTable :: print(int tab) noexcept{
    string tb="";
    for(int i = 0;i<tab;i++){
        tb+="\t";
    }
    cout<<tb<<"ScopeTable# "<<scopeUid<<endl;
    for(int i =0;i<bucketNumber;i++){
        cout<<tb<<i+1<<"--> ";
        for(auto symbol = buckets[i]; symbol != nullptr; symbol = symbol->getNextSymbolInfo()){
            cout<<"<"<<symbol->getSymbolName()<<","<<symbol->getSymbolType()<<"> ";
        }
        cout<<endl;
    }
}

void ScopeTable :: print(ostream& out, bool& isPrint) {

    // out<<endl<<endl<<endl;

    bool canPrint=false;

    for (int i = 0; i < this->bucketNumber; i++)
    {
        SymbolInfo* current = buckets[i];
        if (current==nullptr)
        continue;
        
        canPrint = true;
    }

    if(!canPrint){
        isPrint = true;
        return;
    }else{
        isPrint = false;
    }

    string uid="1";
    if(scopeUid.size()>1) {
        int ct = 0;
        for(int i = 1;i<scopeUid.size();i++) {
            if(scopeUid[i] == '.') continue;
            ct +=scopeUid[i]-'0';

        }
        uid+=".";
        uid+=to_string(ct);
    }

    out <<"ScopeTable # " <<uid<< endl;
    for (int i = 0; i < this->bucketNumber; i++)
    {
        SymbolInfo* current = buckets[i];
        if (current==nullptr)
        continue;
        
        out<<" "<<i<<" --> ";
        while (current != nullptr)
        {
            out<<"< "<<current->getSymbolName()<<" , "<<"ID"<<" > ";
            current = current->getNextSymbolInfo();
        }
        out<<endl;
    }
    
}



//Hash functions
// unsigned long long ScopeTable :: SDBMHash(string symbolName, int bucketNumber) const noexcept{
//     // unsigned long long hash = 0;
//     // unsigned int len = symbolName.length();

//     // for(unsigned int i = 0;i < len; i++){
//     //     hash = ((symbolName[i]) + (hash << 6) + (hash << 16) - hash) % bucketNumber;
//     // }

//     // return hash;

//     // unsigned long long hash = 0;
//     // auto *str = (unsigned char *) p;
//     // int c{};
//     // while ((c = *str++)) {
//     // hash = c + (hash << 6) + (hash << 16) - hash;
//     // }

  
//     // return hash;


//     unsigned long long hash = 0;
//     const unsigned char* str = (const unsigned char*) symbolName.c_str();
//     int c{};
//     while ((c = *str++)) {
//         hash = c + (hash << 6) + (hash << 16) - hash;
//     }
//     return hash % bucketNumber;

// }

unsigned int ScopeTable::SDBMHash(
    string symbolName, 
    unsigned int bucketNumber // Use unsigned to avoid negative values
) const noexcept {
    unsigned int hash = 0; // Critical: Use 32-bit unsigned int
    const unsigned char* str = reinterpret_cast<const unsigned char*>(symbolName.c_str());
    int c{};
    
    while ((c = *str++)) {
        hash = c + (hash << 6) + (hash << 16) - hash; // Matches 32-bit overflow behavior
    }
    
    return hash % bucketNumber; // Now returns correct bucket index
}

// unsigned int sdbmHash(const char *p) {
//     unsigned int hash = 0;
//     auto *str = (unsigned char *) p;
//     int c{};
//     while ((c = *str++)) {
//     hash = c + (hash << 6) + (hash << 16) - hash;
//     }
//     return hash;
//     }

unsigned long long ScopeTable :: DJB2Hash(string s, int bucketNumber) const noexcept {
    unsigned long long hash = 5381;
    for (char c : s)
        hash = ((hash << 5) + hash) + c;
    return hash % bucketNumber;
}

unsigned long long ScopeTable :: FNV1aHash(string s, int bucketNumber) const noexcept{
    unsigned long long hash = 14695981039346656037ULL; 
    const unsigned long long FNV_prime = 1099511628211ULL;

    for (char c : s) {
        hash ^= static_cast<unsigned long long>(c);
        hash *= FNV_prime;
    }

    return hash % bucketNumber;
}

unsigned int ScopeTable :: MurmurHash3(string key, int bucketNumber) const noexcept {
    unsigned int seed = 0x9747b28c;
    unsigned int hash = seed;
    for (char c : key) {
        hash ^= c;
        hash *= 0x5bd1e995;
        hash ^= hash >> 15;
    }
    return hash % bucketNumber;
}





unsigned long long ScopeTable :: getHash(string symbolName, int bucketNumber) const noexcept{
    switch(type) {
        case SDBM: return SDBMHash(symbolName, bucketNumber);
        case DJB2: return DJB2Hash(symbolName, bucketNumber);
        case FNV1A: return FNV1aHash(symbolName, bucketNumber);
        case MURMUR: return MurmurHash3(symbolName,bucketNumber);
        default: return SDBMHash(symbolName, bucketNumber);
    }
}




// //for collison

// bool ScopeTable :: Insert(string symbolName, string symbolType, int& totalCollision) noexcept{
//     int index = getHash(symbolName,this->bucketNumber);

//     SymbolInfo *newSymbolInfo = new SymbolInfo(symbolName,symbolType);

//     if(buckets[index] == nullptr){

//         //
//         cout<<"Inserted in ScopeTable# "<<scopeUid<<" at position "<<(index+1)<<", "<<"1"<<endl;

//         buckets[index] = newSymbolInfo;
//         return true;
//     }

//     totalCollision++;

//     SymbolInfo *current = buckets[index];
//     SymbolInfo *previous = nullptr;
//     int pos = 0;

//     while(current!=nullptr){

//         if(current->getSymbolName() == symbolName){
//             //
//             cout<<"'"<<symbolName<<"' "<<"already exists in the current ScopeTable"<<endl;
//             totalCollision++;
//             return false;
//         }
//         previous = current;
//         current = current->getNextSymbolInfo();
//         pos++;
//     }
//     previous->setNextSymbolInfo(newSymbolInfo);

//     cout<<"Inserted in ScopeTable# "<<scopeUid<<" at position "<<index+1<<", "<<pos+1<<endl;

//     return true;
// }