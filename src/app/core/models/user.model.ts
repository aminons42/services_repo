export interface User {
    id: number;
    username: string;
    nom: string;
    prenom: string;
    roles: Role[];

}
export interface Role {
    id:number;
    nom: string;
}